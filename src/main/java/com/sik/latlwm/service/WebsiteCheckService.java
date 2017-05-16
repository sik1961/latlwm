package com.sik.latlwm.service;

import com.crossedstreams.desktop.website.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;
import java.util.concurrent.*;
//import java.util.logging.Logger;

/**
 * Created by hisg401 on 16/05/2017.
 */
public class WebsiteCheckService {

	private static final Logger LOG = LoggerFactory.getLogger(WebsiteCheckService.class);

	private Collection<UrlGroup> urlGroups;
	private WebsiteCheck checker;
	@Autowired
	private UrlGroupService groupService;
	@Autowired
	private ExpectationChecker expectationChecker;
	@Autowired
	private EmailService emailService;

	ExpectationChecker.ExpectationResult

	private ExpectationChecker.Callback callback = new ExpectationChecker.Callback() {
		public void onResult(ExpectationChecker.ExpectationResult expectationResult, UrlDefinition urlDefinition) {
			if(expectationResult.isMet()) {
				LOG.info("   OK :- " + urlDefinition.toString());
			} else {
				LOG.info("ERROR :- " + urlDefinition.toString() + " - " + expectationResult.toString());
				emailService.notify();
			}
		}
	};

	public WebsiteCheckService(WebsiteCheck checker) {
		this.checker = checker;
	}

	//@Scheduled(initialDelay = 1000L * 60, fixedDelay = 1000L * 60 * 10)
	public void startTask() {

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
		Task task1 = new Task ("Demo Task 1");
		Task task2 = new Task ("Demo Task 2");

		System.out.println("The time is : " + new Date());

		executor.schedule(task1, 5 , TimeUnit.SECONDS);
		executor.schedule(task2, 10 , TimeUnit.SECONDS);

		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		executor.shutdown();




		this.checker = new WebsiteCheck(groupService.getGroups(), expectationChecker);
		for (UrlGroup group: groupService.getGroups()) {
			for(UrlDefinition url: group.getUrlDefinitions()) {
				expectationChecker.check(url,callback);
				if (callback.)
			}
		};
	}

}
