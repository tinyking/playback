package com.eflag.ocloud3.playback.model;

import java.util.ArrayList;
import java.util.List;

public class Field {
	private String name;
	private String showName;
	private int size;
	private int pos;
	private List<Field> fields;

	public Field() {
		this.fields = new ArrayList<Field>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	

	public List<Field> getFields() {
		return fields;
	}

//	public void setFields(List<Field> fields) {
//		this.fields = fields;
//	}
	
	public void addField(Field field) {
		fields.add(field);
	}

	@Override
	public String toString() {
		return "Field [name=" + name + ", showName=" + showName + ", size=" + size + ", pos=" + pos + "]";
	}

}
