package com.sik.latlwm.core;

import com.crossedstreams.desktop.website.UrlExpectation;
import com.crossedstreams.desktop.website.UrlGroup;

import java.util.UUID;

/**
 * Created by hisg401 on 17/05/2017.
 */
public class UserSiteService {
	private String id;
	private String name;
	private String email;
	private UrlExpectation expectation;
	private UrlGroup group;

	public UserSiteService() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserSiteService withName(String name) {
		this.name = name;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserSiteService withEmail(String email) {
		this.email = email;
		return this;
	}

	public UrlExpectation getExpectation() {
		return expectation;
	}

	public UserSiteService withExpectation(UrlExpectation expectation) {
		this.expectation = expectation;
		return this;
	}

	public UrlGroup getGroup() {
		return group;
	}

	public void setGroup(UrlGroup group) {
		this.group = group;
	}

	public UserSiteService withGroup(UrlGroup group) {
		this.group = group;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		UserSiteService that = (UserSiteService) o;

		return new org.apache.commons.lang3.builder.EqualsBuilder()
				.append(getId(), that.getId())
				.append(getName(), that.getName())
				.append(getEmail(), that.getEmail())
				.append(getExpectation(), that.getExpectation())
				.append(getGroup(), that.getGroup())
				.isEquals();
	}

	@Override
	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
				.append(getId())
				.append(getName())
				.append(getEmail())
				.append(getExpectation())
				.append(getGroup())
				.toHashCode();
	}

	@Override
	public String toString() {
		return new org.apache.commons.lang3.builder.ToStringBuilder(this)
				.append("id", id)
				.append("name", name)
				.append("email", email)
				.append("expectation", expectation)
				.append("group", group)
				.toString();
	}
}
