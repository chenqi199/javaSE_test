package com.cn.myhttptest;

public class Header {

	private String name;
	private String value;

	public Header() {
		super();
	}

	public Header(String key, String value) {
		super();
		this.name = key;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String key) {
		this.name = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString() {
		return name + "=" + value;
	}

}
