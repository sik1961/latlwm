package com.sik.latlwm.core;

import com.crossedstreams.desktop.website.ExpectationChecker;
import com.crossedstreams.desktop.website.UrlDefinition;
import com.sik.latlwm.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by hisg401 on 17/05/2017.
 */
public class UserCallback implements ExpectationChecker.Callback {

	@Autowired
	private EmailService emailService;

	private UserSiteService userSite;

	public UserCallback(UserSiteService userSite) {
		super();
		this.userSite = userSite;
	}

	public void onResult(ExpectationChecker.ExpectationResult result, UrlDefinition def) {
		String resultExplanation = String.format("%s %s (%s) %s", new Object[]{result.isMet()?"OK ":"ERR", def.getLabel(), def.getUrl(), result.getExplanation()});
		String subject = String.format("LatLWM: %s - %s (%s) %s", result.isMet()?"OK ":"ERR", def.getUrl());
		if (!result.isMet()) {
			emailService.notify(userSite.getEmail(), subject, resultExplanation);
		}

	}

}
