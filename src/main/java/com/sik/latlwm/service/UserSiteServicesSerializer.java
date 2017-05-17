package com.sik.latlwm.service;

import com.google.gson.*;
import com.sik.latlwm.core.UserSiteServices;

import java.lang.reflect.Type;

/**
 * Created by hisg401 on 17/05/2017.
 */
public class UserSiteServicesSerializer implements JsonSerializer<UserSiteServices> {
	public JsonElement serialize(final UserSiteServices userSiteServices, final Type type, final JsonSerializationContext context) {
		JsonObject result = new JsonObject();
		result.add("userSiteServices", new JsonPrimitive( userSiteServices);

		return result;
	}
}
