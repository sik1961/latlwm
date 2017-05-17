package com.sik.latlwm.service;

import com.crossedstreams.desktop.website.*;
import com.sik.latlwm.core.UserSiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.concurrent.*;
//import java.util.logging.Logger;

/**
 * Created by hisg401 on 16/05/2017.
 */
public class WebsiteCheckService {

	private static final long INTERVAL_MILLIS = 1000L * 60 * 15;
	private static final long MINUTE_MILLIS = 1000L * 60;
	private static final Logger LOG = LoggerFactory.getLogger(WebsiteCheckService.class);

	@Autowired
	private UserSiteServiceService groupService;

	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);


	public WebsiteCheckService() {}

	@Scheduled(initialDelay = MINUTE_MILLIS, fixedDelay = MINUTE_MILLIS)
	public void startTasks() {

		long startTime = System.currentTimeMillis();
		LOG.info("Starting tasks at: " + new Date());

		Collection<Future> tasks = new ArrayList<Future>();

		/**
		 * Submit tasks
		 */
		for (UserSiteService site: groupService.getGroups()) {
			tasks.add(executor.submit(new WebsiteCheckTask(site)));
		}

		/**
		 * Monitor tasks
		 */
		try {
			do {
				Thread.sleep(MINUTE_MILLIS);
				if (!isTasksActive(tasks)) {
					break;
				}
			} while(System.currentTimeMillis() < startTime + (INTERVAL_MILLIS - MINUTE_MILLIS));
		} catch (InterruptedException e) {
			LOG.error("Awaiting tasks interrupted");
		}

		/**
		 * End tasks
		 */
		try {
			if (isTasksActive(tasks)) {
				LOG.info("Issuing cancel for remaining tasks");
				cancelTasks(tasks);
				Thread.sleep(MINUTE_MILLIS);
			}
		} catch (InterruptedException e) {
			LOG.error("Awaiting cancel interrupted");
		}

		LOG.info("Issuing executor shutdown at: " + new Date());
		executor.shutdown();

		long endTime = System.currentTimeMillis();
		LOG.info("Tasks complete: " + isTasksActive(tasks) + " in " + (endTime - startTime) + "ms");
	}

	private boolean isTasksActive(Collection<Future> tasks) {
		int activeCount = 0;
		for (Future t:tasks) {
			LOG.debug("Task: " + t + " done=" + t.isDone() + " canc=" + t.isCancelled());
			if (!t.isDone()) {
				activeCount++;
			} else {
				tasks.remove(t);
			}
		}
		LOG.info(activeCount + " active tasks!");
		return activeCount > 0;
	}

	private void cancelTasks(Collection<Future> tasks) {
		for (Future t:tasks) {
			if (!t.isDone()) {
				LOG.info("Cancelling:" + t.toString());
				t.cancel(true);
			}
		}
	}

}
