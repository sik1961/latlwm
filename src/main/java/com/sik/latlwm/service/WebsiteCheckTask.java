package com.sik.latlwm.service;

import com.crossedstreams.desktop.website.*;
import com.sik.latlwm.core.UserCallback;
import com.sik.latlwm.core.UserSiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Collection;

/**
 * Created by hisg401 on 16/05/2017.
 */
public class WebsiteCheckTask implements Runnable {
	private static final Logger LOG = LoggerFactory.getLogger(WebsiteCheckTask.class);

	private UserSiteService userSite;

	public WebsiteCheckTask(UserSiteService userSite) {
		super();
		this.userSite = userSite;
	}

	public void run() {
		for(UrlDefinition url: userSite.getGroup().getUrlDefinitions()) {
			LOG.info("Checking: " + url);
			new JavaURLBasedExpectationChecker().check(url,new UserCallback(userSite));
		}
	}

}
