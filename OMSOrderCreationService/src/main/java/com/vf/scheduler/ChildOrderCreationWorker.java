/*
 * 
 */
package com.vf.scheduler;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.vf.dao.ChildOrderDAO;
import com.vf.exception.ChildOrderNotFoundException;
import com.vf.model.ChildOrder;
import com.vf.util.Constants;

// TODO: Auto-generated Javadoc
/**
 * The Class WorkerThreadCreateChildOrder.
 *
 * @author nb19705
 */

@Component
public class ChildOrderCreationWorker implements Runnable {

	@Autowired
	ChildOrderDAO childOrderDAO;

	@Value("${custom.order.creation.simulator.delay}")
	int orderCreationDelay;

	@Value("${custom.order.creation.thread.sleep.time}")
	int threadSleepTime;

	@Override
	public void run() {
		while (true) {
			final long threadId = Thread.currentThread().getId();
			ChildOrder child = null;
			try {
				child = childOrderDAO.pickOpenOrderForSubmissionToOMS(Constants.OPEN_STATUS);
				Thread.currentThread().setName("ChildOrder:" + child.getId());
			} catch (NullPointerException e1) {
				System.out.println("<thread-id>"+threadId+ " No more order to process!!!!!!!");
			} catch (ChildOrderNotFoundException e) {
				System.out.println("<thread-id>"+threadId+ " No more order to process!!!!!!!");
				// TODO Auto-generated catch block
			}
			while (child != null) {
				try {
					processChild(child,threadId);
				} catch (ChildOrderNotFoundException e) {
					// TODO Auto-generated catch block
				}
				child = null;
				try {
					child = childOrderDAO.pickOpenOrderForSubmissionToOMS(Constants.OPEN_STATUS);

				} catch (NullPointerException e1) {
					System.out.println("<thread-id>"+threadId+ " No more order to process!!!!!!!");
				} catch (ChildOrderNotFoundException e) {
					// TODO Auto-generated catch block
				}
			}
			try {

				if (Thread.currentThread().isInterrupted()) {
					System.out.println("[CHILD THREAD] " + threadId + " Thread interrupted, exiting");
					return;
				}

				Thread.sleep(threadSleepTime);

				if (Thread.currentThread().isInterrupted()) {
					System.out.println("[CHILD THREAD] " + threadId + " Thread interrupted, exiting");
					return;
				}

			} catch (InterruptedException e) {
				System.out.println("[CHILD THREAD] " + threadId + " Thread interrupted, exiting");
				return;
			}
		}
	}

	public String processChild(ChildOrder child, Long threadId) throws ChildOrderNotFoundException {

		String payload = child.getPayload();
		System.out.println("<thread-id>"+threadId+ " Start order create in OMS");
		String orderId = createOrderInOMS(payload);
		child.setOmsOrderCreationDate(new Date());
		child.setOrderStatus(Constants.CREATED_STATUS);
		child.setOmsOrderId(orderId);
		childOrderDAO.updateOrderStatus(child);
		System.out.println("<thread-id>"+threadId+ " Order created in OMS with order id=" + orderId);
		return orderId;
	}

	public String createOrderInOMS(String payload) {

		// Putting some delay to simulate order creation time
		try {
			Thread.sleep(orderCreationDelay);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}

		return String.valueOf(randomNumberGenerate());
	}

	public int randomNumberGenerate() {

		Random rand = new Random(); // instance of random class
		int upperbound = 9000;
		// generate random values from 0-100
		int int_random = rand.nextInt(upperbound);
		return int_random;
	}

}