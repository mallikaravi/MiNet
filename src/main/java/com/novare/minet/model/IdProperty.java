package com.novare.minet.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(scope = IdProperty.class, generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id", resolver = MiNetObjectIdResolver.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "uuid")
public class IdProperty {
//	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	public void generateId() {
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdProperty other = (IdProperty) obj;
		return getId() == other.getId();
	}

}
