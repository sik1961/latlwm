package com.sik.latlwm.service;

import com.crossedstreams.desktop.website.JsonParser;
import com.crossedstreams.desktop.website.UrlGroup;
import com.google.gson.*;
import com.sik.latlwm.core.UserSiteService;
import com.sik.latlwm.core.UserSiteServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

/**
 * Created by hisg401 on 16/05/2017.
 */
public class UserSiteServiceServiceImpl implements UserSiteServiceService {

	@Value("latlwm.json.file") private String jsonFile;

	private static final Logger LOG = LoggerFactory.getLogger(UserSiteServiceServiceImpl.class);

	private UserSiteServices groups;

	private GsonBuilder gsonBuilder = new GsonBuilder();

	public UserSiteServiceServiceImpl() {
		super();
		groups = new UserSiteServices();
	}

	public Collection<UserSiteService> getGroups() {
		this.readGroups();
		return this.groups.getUserSiteServices();
	}

	public String addGroup(UserSiteService group) {
		this.groups.getUserSiteServices().add(group);
		return group.getId();
	}

	private void readGroups() {
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		try {
			this.groups = gson.fromJson(new FileReader(new File(jsonFile)), UserSiteServices.class);
		} catch (FileNotFoundException e) {
			LOG.error("Error reading file: " + jsonFile);
		}

	}

	private void writeGroups() {
		Gson gson = gsonBuilder.setPrettyPrinting().create();

		FileWriter writer = null;
		try {
			writer = new FileWriter(new File(jsonFile));
			writer.write(gson.toJson(this.groups));
			writer.close();
		} catch (IOException e) {
			LOG.error("I/O Error writing to file: " + jsonFile);
		}
	}

}
