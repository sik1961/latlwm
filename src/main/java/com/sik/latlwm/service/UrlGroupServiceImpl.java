package com.sik.latlwm.service;

import com.crossedstreams.desktop.website.JsonParser;
import com.crossedstreams.desktop.website.UrlGroup;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Collection;
import java.util.TreeSet;

/**
 * Created by hisg401 on 16/05/2017.
 */
public class UrlGroupServiceImpl implements UrlGroupService {

	@Value("latlwm.json.dir") private String jsonDir;

	private FilenameFilter jsonFilter = new FilenameFilter() {
		public boolean accept(File dir, String name) {
			String lowercaseName = name.toLowerCase();
			if (lowercaseName.endsWith(".json")) {
				return true;
			} else {
				return false;
			}
		}
	};

	public Collection<UrlGroup> getGroups() {
		JsonParser parser = new JsonParser();
		Collection<UrlGroup> groups = new TreeSet<UrlGroup>();

		if (new File(jsonDir).isDirectory()) {
			for (File f : new File(jsonDir).listFiles(jsonFilter)) {
				try {
					FileReader reader = new FileReader(f);
					groups.addAll(parser.parseUrlGroups(reader));
					reader.close();
				} catch (IOException e) {
					throw new IllegalStateException("I/O Error on file: " + jsonDir + "/" + f);
				}
			}
			return groups;
		} else {
			throw new IllegalArgumentException("Error :- " + jsonDir + " not a directory");
		}
	}
}
