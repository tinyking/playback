package com.eflag.ocloud3.playback.model;

import java.util.ArrayList;
import java.util.List;

public class Pdml {

	private String version;
	private String creator;
	private String time;
	private String captureFile;
	private List<Packet> packets;
	

	public Pdml() {
		super();
		this.packets = new ArrayList<Packet>();
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCaptureFile() {
		return captureFile;
	}

	public void setCaptureFile(String captureFile) {
		this.captureFile = captureFile;
	}

	public List<Packet> getPackets() {
		return packets;
	}

//	public void setPackets(List<Packet> packets) {
//		this.packets = packets;
//	}

	public void addPacket(Packet packet) {
		packets.add(packet);
	}

	@Override
	public String toString() {
		return "Pdml [version=" + version + ", creator=" + creator + ", time=" + time + ", captureFile=" + captureFile
				+ ", packets=" + packets + "]";
	}

}
