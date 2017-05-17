package com.sik.latlwm.service;

import com.crossedstreams.desktop.website.ExpectationChecker;
import com.crossedstreams.desktop.website.UrlDefinition;

/**
 * Created by hisg401 on 16/05/2017.
 */
public interface EmailService {
	boolean notify(String emailAddress, String subject, String text);
}
