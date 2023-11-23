package com.yaps.petstore.domain.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.domain.dao.CounterRepository;
import com.yaps.petstore.domain.model.Counter;
import com.yaps.petstore.logging.Trace;

@Service
public class CounterServiceImpl implements CounterService {
	
	// Used for logging
    private final String _cname = this.getClass().getName();
    
    @Autowired
    private CounterRepository counterRepository;

	@Override
	@Transactional
	public String getUniqueId(String name) {
		final String mname = "getUniqueId";
        Trace.entering(_cname, mname, name);
		int nextId = 0;
		Counter counter = null ;
		try {
			counter = counterRepository.findById(name).get();
			nextId = counter.getValue()+1;
			counter.setValue(nextId);
			counterRepository.save(counter);
		} catch (NoSuchElementException e) {
			counter = new Counter();
			counter.setName(name);
			nextId = 1;
			counter.setValue(nextId);
			counterRepository.save(counter);
		}		
		Trace.exiting(_cname, mname, new Integer(nextId));
		return String.valueOf(nextId);
	}

	@Override
	@Transactional
	public void deleteById(String name) {
		final String mname = "deleteById";
        Trace.entering(_cname, mname, name);
		counterRepository.deleteById(name);
		Trace.exiting(_cname, mname);
	}

}
