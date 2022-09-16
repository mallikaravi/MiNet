package com.novare.minet.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.novare.minet.util.MiNetObjectIdResolver;

@JsonIdentityInfo(scope = IdProperty.class, generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id1", resolver = MiNetObjectIdResolver.class)
public class IdProperty {
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Integer id;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
