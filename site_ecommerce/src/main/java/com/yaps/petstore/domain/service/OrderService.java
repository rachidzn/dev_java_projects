package com.yaps.petstore.domain.service;

import java.util.Map;

import com.yaps.petstore.domain.dto.OrderDTO;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.RemoveException;


public interface OrderService {
    
    public Long createOrder(String customerId, Map<String,Integer> shoppingCart) throws CreateException, CheckException ;
    
    public OrderDTO createOrder(final OrderDTO orderDTO) throws CreateException, CheckException ;

    public OrderDTO findOrder(final long orderId) throws FinderException, CheckException ;

    public void deleteOrder(final long orderId) throws RemoveException, CheckException ;

}
