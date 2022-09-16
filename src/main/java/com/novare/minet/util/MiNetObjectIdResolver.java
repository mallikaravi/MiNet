package com.novare.minet.util;

import com.fasterxml.jackson.annotation.ObjectIdGenerator.IdKey;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;
import com.novare.minet.model.IdProperty;

//Ref: https://github.com/FasterXML/jackson-databind/issues/266
public class MiNetObjectIdResolver extends SimpleObjectIdResolver {
	@Override
	public void bindItem(IdKey id, Object ob) {
		if (ob instanceof IdProperty) {
			((IdProperty) ob).setId((Integer) id.key);
		}
		super.bindItem(id, ob);

	}

	@Override
	public ObjectIdResolver newForDeserialization(Object context) {
		return new MiNetObjectIdResolver();
	}
}