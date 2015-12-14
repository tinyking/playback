package com.eflag.ocloud3.playback.model;

import java.util.ArrayList;
import java.util.List;

public class Packet {
	private int id;
	private Brief brief;
	private List<Proto> protos;
	private Frame frame;
	private Color color;
	private Interface intface;
	private Direction direction;
	private Binary binary;

	public Packet() {
		this.protos = new ArrayList<Proto>();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Brief getBrief() {
		return brief;
	}

	public void setBrief(Brief brief) {
		this.brief = brief;
	}

	public List<Proto> getProtos() {
		return protos;
	}

//	public void setProtos(List<Proto> protos) {
//		this.protos = protos;
//	}

	public void addProto(Proto proto) {
		protos.add(proto);
	}
	
	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Interface getIntface() {
		return intface;
	}

	public void setIntface(Interface intface) {
		this.intface = intface;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Binary getBinary() {
		return binary;
	}

	public void setBinary(Binary binary) {
		this.binary = binary;
	}

	@Override
	public String toString() {
		return "Packet [id=" + id + ", brief=" + brief + ", protos=" + protos + ", frame=" + frame + ", color=" + color
				+ ", intface=" + intface + ", direction=" + direction + ", binary=" + binary + "]";
	}


}
