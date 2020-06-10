package com.vf.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vf.dao.ChildOrderDAO;
import com.vf.exception.ChildOrderNotFoundException;
import com.vf.model.ChildOrder;

@Component
@EnableScheduling
public class FetchAndUpdate {
	@Autowired
    ChildOrderDAO childOrderDao;
	
	@Scheduled(fixedDelay=5000L)
	void runScheduler() throws ChildOrderNotFoundException {
		System.out.println("schedular running");

		ExecutorService pool = Executors.newFixedThreadPool(2);
	
		for (ChildOrder co : childOrderDao.getAllOrders())
		{
			pool.execute(new Runnable() {
		        @Override
		        public void run() {
		        	System.out.println(Thread.currentThread().getName());
		        	System.out.println("Address "+co.getAddress()+"Mobile Number "+co.getMobileNumber()+" Order Status "+co.getOrderStatus());
		    		if(co.getOrderStatus().equals("PENDING"))
		    			co.setOrderStatus("SUBMITTED");
		    			try {
							childOrderDao.updateOrderStatus(co.getId(), co);
						} catch (ChildOrderNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			}
		    });
			
		}
		
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		}
		
		
	}

