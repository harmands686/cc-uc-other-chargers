package com.vf.scheduler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vf.exception.ChildOrderNotFoundException;

@Component
@EnableScheduling
public class ScheduledProcessor {
	
	@Autowired
	ChildOrderCreationWorker childOrderCreationWorker;

	@Value("${custom.max.child.order.creation.threads}")
	private int maxThreadNumberChild;

	@Transient
	ExecutorService pool;
	
	Long test = 9990L;

	@Scheduled(fixedDelay = 10000L)
	void runScheduler() throws ChildOrderNotFoundException {
		try {
			System.out.println("Scheduling workers....");
			System.out.println("Started "+ maxThreadNumberChild +" worker threads");
			pool = Executors.newCachedThreadPool();
			for (int i = 0; i < maxThreadNumberChild; i++) {
				pool.submit(childOrderCreationWorker);
			}
			pool.shutdown();
			if (!pool.isShutdown()) {
				pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
			}
			pool.awaitTermination(1, TimeUnit.MINUTES);

		} catch (InterruptedException ex) {
			pool.shutdownNow();
		}
	}

}
