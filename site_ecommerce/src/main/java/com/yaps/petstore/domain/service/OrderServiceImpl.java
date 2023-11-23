package com.yaps.petstore.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.authentication.domain.dao.UserRepository;
import com.yaps.petstore.authentication.domain.dto.UserDTO;
import com.yaps.petstore.authentication.domain.model.User;
import com.yaps.petstore.authentication.domain.service.UserService;
import com.yaps.petstore.domain.dao.ItemRepository;
import com.yaps.petstore.domain.dao.OrderLineRepository;
import com.yaps.petstore.domain.dao.OrderRepository;
import com.yaps.petstore.domain.dto.OrderDTO;
import com.yaps.petstore.domain.dto.OrderLineDTO;
import com.yaps.petstore.domain.model.CreditCard;
import com.yaps.petstore.domain.model.Item;
import com.yaps.petstore.domain.model.Order;
import com.yaps.petstore.domain.model.OrderLine;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.logging.Trace;

/**
 * This class is a facade for all order services.
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	//@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    
 // Used for logging
    protected final transient String _cname = this.getClass().getName();
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderLineRepository orderLineRepository;
    
    @Autowired
    private UserRepository customerRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private CreditCardService creditCardService;

    // ======================================
    // =            Constructors            =
    // ======================================
    public OrderServiceImpl() { }

    // ======================================
    // =           Business methods         =
    // ======================================
    @Override
    public Long createOrder(String customerId, Map<String,Integer> shoppingCart) throws CreateException, CheckException {
        final String mname = "createOrder";
        Trace.entering(_cname, mname);
        
        OrderDTO orderDTO = new OrderDTO();
        UserDTO customerDTO = null;
        Collection<OrderLineDTO> orderLines= new ArrayList<>();
        
     // Finds the customer
        try {
			customerDTO = userService.findUser(customerId);
		} catch (FinderException e) {
			e.printStackTrace();
			throw new CreateException("Customer must exist to create an order");
		}
        orderDTO.setCustomerId(customerId);
		orderDTO.setFirstname(customerDTO.getFirstname());
		orderDTO.setLastname(customerDTO.getLastname());
		orderDTO.setStreet1(customerDTO.getStreet1());
		orderDTO.setStreet2(customerDTO.getStreet2());
		orderDTO.setCity(customerDTO.getCity());
		orderDTO.setZipcode(customerDTO.getZipcode());
		orderDTO.setState(customerDTO.getState());
		orderDTO.setCountry(customerDTO.getCountry());
		orderDTO.setCreditCardType(customerDTO.getCreditCardType());
		orderDTO.setCreditCardNumber(customerDTO.getCreditCardNumber());
		orderDTO.setCreditCardExpiryDate(customerDTO.getCreditCardExpiryDate());
        
		Set<String> items = shoppingCart.keySet();
		Iterator<String> iter = items.iterator();
		while(iter.hasNext()) {
			String itemId = iter.next();
			Item item = itemRepository.findById(itemId).get();
			OrderLineDTO orderLineDTO = new OrderLineDTO();
			orderLineDTO.setItemId(item.getId());
			orderLineDTO.setUnitCost(item.getUnitCost());
			orderLineDTO.setItemName(item.getName());
			orderLineDTO.setQuantity((int)shoppingCart.get(itemId));
			orderLines.add(orderLineDTO);
		}
		orderDTO.setOrderLines(orderLines);
		orderDTO = createOrder(orderDTO);
		return orderDTO.getId();
    }
    
    @Override
    @Transactional
    public OrderDTO createOrder(final OrderDTO orderDTO) throws CreateException, CheckException {
        final String mname = "createOrder";
        Trace.entering(_cname, mname, orderDTO);

        if (orderDTO == null)
            throw new CreateException("Order object is null");

        if (orderDTO.getOrderLines() == null || orderDTO.getOrderLines().size() <= 0)
            throw new CheckException("There are no order lines");

        // Finds the customer
        User customer=null;
        
        if( ! customerRepository.findById(orderDTO.getCustomerId()).isPresent())
        	throw new CreateException("Customer must exist to create an order");
        else 
        	customer=customerRepository.findById(orderDTO.getCustomerId()).get();
        
        try {
			if(findOrder(orderDTO.getId())!=null)
				 throw new DuplicateKeyException();
		} catch (FinderException  | CheckException e) {}

        // Transforms Order DTO into domain object
        final Order order = new Order(  orderDTO.getFirstname(), orderDTO.getLastname(),
                                        orderDTO.getStreet1(), orderDTO.getCity(),
                                        orderDTO.getZipcode(), orderDTO.getCountry(), customer);
        order.setStreet2(orderDTO.getStreet2());
        order.setState(orderDTO.getState());
        // ======================================
    	// = Credit Card Check =
    	// ======================================
        if(	orderDTO.getCreditCardNumber()!=null && orderDTO.getCreditCardType()!=null
       		 && orderDTO.getCreditCardExpiryDate()!=null ) {
       	
       	if(! orderDTO.getCreditCardNumber().equals("") && ! orderDTO.getCreditCardType().equals("")
       			&& ! orderDTO.getCreditCardExpiryDate().equals("")) {
        logger.info("in CC check - "+orderDTO.getCreditCardNumber()+" - "+orderDTO.getCreditCardType()+" - "+orderDTO.getCreditCardExpiryDate());
        CreditCard cc = new CreditCard();
        cc.setCreditCardNumber(orderDTO.getCreditCardNumber());
        cc.setCreditCardType(orderDTO.getCreditCardType());
        cc.setCreditCardExpiryDate(orderDTO.getCreditCardExpiryDate());
        creditCardService.verifyCreditCard(cc);
        
        order.setCreditCardExpiryDate(orderDTO.getCreditCardExpiryDate());
        order.setCreditCardNumber(orderDTO.getCreditCardNumber());
        order.setCreditCardType(orderDTO.getCreditCardType());
        }        
        }

        order.checkData();
        // Creates the order
        orderRepository.save(order);

        // Creates all the orderLines linked with the order
        for (Iterator<OrderLineDTO> iterator = orderDTO.getOrderLines().iterator(); iterator.hasNext();) {
            final OrderLineDTO orderLineDTO = iterator.next();
            // Finds the item
            Item item = null;
            if( ! itemRepository.findById(orderLineDTO.getItemId()).isPresent())
            	throw new CreateException("Item must exist to create an order line");
            else 
            	item = itemRepository.findById(orderLineDTO.getItemId()).get();
            // Transforms OrderLine DTO into domain object
            final OrderLine orderLine = new OrderLine(orderLineDTO.getQuantity(), orderLineDTO.getUnitCost(), order, item);
            // Creates the order line
            orderLine.checkData();
            orderLineRepository.save(orderLine);
        }
        // Transforms domain object into DTO
        final OrderDTO result = transformOrder2DTO(order);
        return result;
    }

    @Override
    @Transactional(readOnly=true)
    public OrderDTO findOrder(final long orderId) throws FinderException, CheckException { 
        final String mname = "findOrder";
        Trace.entering(_cname, mname, orderId);

        checkId(orderId);
        // Finds the object
        Order order = null;
        if( ! orderRepository.findById(orderId).isPresent())
        	throw new FinderException("Order must exist to be found");
        else 
        	order=orderRepository.findById(orderId).get();

        // Transforms domain object into DTO
        final OrderDTO orderDTO = transformOrder2DTO(order);

        Trace.exiting(_cname, mname, orderDTO);
        return orderDTO;
    }

    @Override
    @Transactional
    public void deleteOrder(final long orderId) throws RemoveException, CheckException {
        final String mname = "deleteOrder";
        Trace.entering(_cname, mname, orderId);
        checkId(orderId);
        Order order;
        // Checks if the object exists
        if( ! orderRepository.findById(orderId).isPresent())
        	throw new RemoveException("Order must exist to be deleted");
        else 
        	order=orderRepository.findById(orderId).get();
        // Deletes the object
        orderRepository.delete(order);
        for(OrderLine orderLine : orderLineRepository.findAllByOrder(order)) {
        	orderLineRepository.delete(orderLine);
        }
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private OrderDTO transformOrder2DTO(final Order order) {
        final OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setCity(order.getCity());
        orderDTO.setCountry(order.getCountry());
        if(order.getCreditCard()!=null) {
            orderDTO.setCreditCardExpiryDate(order.getCreditCardExpiryDate());
            orderDTO.setCreditCardNumber(order.getCreditCardNumber());
            orderDTO.setCreditCardType(order.getCreditCardType());
            }
        orderDTO.setCustomerId(order.getCustomer().getUsername());
        orderDTO.setFirstname(order.getFirstname());
        orderDTO.setLastname(order.getLastname());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setState(order.getState());
        orderDTO.setStreet1(order.getStreet1());
        orderDTO.setStreet2(order.getStreet2());
        orderDTO.setZipcode(order.getZipcode());
        // Transforms all the order lines
        Collection<OrderLine> orderlines = orderLineRepository.findAllByOrder(order);
        orderDTO.setOrderLines(transformOrderLines2DTOs(orderlines));
        return orderDTO;
    }

    private Collection<OrderLineDTO> transformOrderLines2DTOs(final Collection<OrderLine> orderLines) {
        final Collection<OrderLineDTO> orderLinesDTO = new ArrayList<>();
        OrderLineDTO orderLineDTO;
        for (Iterator<OrderLine> iterator = orderLines.iterator(); iterator.hasNext();) {
            final OrderLine orderLine = (OrderLine) iterator.next();
            orderLineDTO = new OrderLineDTO();
            orderLineDTO.setItemId(orderLine.getItem().getId());
            orderLineDTO.setItemName(orderLine.getItem().getName());
            orderLineDTO.setQuantity(orderLine.getQuantity());
            orderLineDTO.setUnitCost(orderLine.getUnitCost());
            orderLinesDTO.add(orderLineDTO);
        }
        return orderLinesDTO;
    }
    
    private void checkId(final long l) throws CheckException {
    	if ( l == 0)
    		throw new CheckException("Id should not be 0");    	
    }
    
}
