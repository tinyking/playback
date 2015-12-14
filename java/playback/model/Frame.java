package com.eflag.ocloud3.playback.model;

public class Frame {

	private int len;
	private String value;

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Frame [len=" + len + ", value=" + value + "]";
	}

	
}
