package com.eflag.ocloud3.playback.model;

public class Brief {

	private String time;
	private String src;
	private String dst;
	private String protocol;
	private int length;
	private String info;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "Brief [time=" + time + ", src=" + src + ", dst=" + dst + ", protocol=" + protocol + ", length=" + length
				+ ", info=" + info + "]";
	}

	
}
