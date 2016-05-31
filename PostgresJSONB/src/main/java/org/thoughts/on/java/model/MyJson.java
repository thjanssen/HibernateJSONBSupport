package org.thoughts.on.java.model;

import java.io.Serializable;

public class MyJson implements Serializable {

	private String stringProp;
	
	private Long longProp;

	public String getStringProp() {
		return stringProp;
	}

	public void setStringProp(String stringProp) {
		this.stringProp = stringProp;
	}

	public Long getLongProp() {
		return longProp;
	}

	public void setLongProp(Long longProp) {
		this.longProp = longProp;
	}
}
