package com.sik.latlwm.service;

import com.crossedstreams.desktop.website.ExpectationChecker;
import com.crossedstreams.desktop.website.UrlDefinition;
import com.crossedstreams.desktop.website.UrlGroup;
import com.crossedstreams.desktop.website.WebsiteCheck;
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

	private UrlGroup urlGroup;
	private WebsiteCheck checker;
	@Autowired
	private ExpectationChecker expectationChecker;
	@Autowired
	private EmailService emailService;

	ExpectationChecker.ExpectationResult

	private ExpectationChecker.Callback callback;

	public WebsiteCheckTask(WebsiteCheck checker, UrlGroup urlGroup) {
		this.checker = checker;
		this.urlGroup = urlGroup;
	}

	public void run() {
		this.checkGroup();
	}

	public void checkGroup() {
		for(UrlDefinition url: urlGroup.getUrlDefinitions()) {
				expectationChecker.check(url,callback);
			}
		}
	}
}
