package com.sik.latlwm.core;

import java.util.Collection;

/**
 * Created by hisg401 on 17/05/2017.
 */
public class UserSiteServices {
	private Collection<UserSiteService> userSiteServices;

	public UserSiteServices() {}

	public Collection<UserSiteService> getUserSiteServices() {
		return userSiteServices;
	}

	public void setUserSiteServices(Collection<UserSiteService> userSiteServices) {
		this.userSiteServices = userSiteServices;
	}
}
