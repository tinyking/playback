package com.eflag.ocloud3.playback.parse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.JsonMappingException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.eflag.ocloud3.JacksonUtils;
import com.eflag.ocloud3.playback.model.Binary;
import com.eflag.ocloud3.playback.model.Brief;
import com.eflag.ocloud3.playback.model.Color;
import com.eflag.ocloud3.playback.model.Direction;
import com.eflag.ocloud3.playback.model.Field;
import com.eflag.ocloud3.playback.model.Frame;
import com.eflag.ocloud3.playback.model.Interface;
import com.eflag.ocloud3.playback.model.Packet;
import com.eflag.ocloud3.playback.model.Pdml;
import com.eflag.ocloud3.playback.model.Proto;

public class PlaybackXmlHandler extends DefaultHandler {

	static enum Node {
		xml,
		pdml,
		packet,
		brief,
		proto,
		field,
		Frame,
		Color,
		Interface,
		Direction,
		Binary
		;


		public static Node toNode(String name) {
			Node node = xml;
			for (Node n : Node.values()) {
				if (n.name().equals(name)) {
					node = n;
					break;
				}
			}
			return node;
		}
	}

	static enum Attr {
		version,
		creator,
		capture_file,
		id,
		name,
		showname,
		size,
		pos,
		time,
		src,
		dst,
		protocol,
		length,
		info,
		len,
		color,
		intf,
		direction,
		bid
	}

	private Pdml pdml;
	private Proto proto;
	private Frame frame;
	private boolean hasText = false;
	private StringBuilder nodeText;
	private int level = 0;

	private Map<Integer, Field> fieldMap = new HashMap<Integer, Field>();


	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		super.endDocument();
		try {
			System.out.println(JacksonUtils.toJson(pdml));
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);

		switch (Node.toNode(qName)) {
		case pdml:
			parsePdml(attributes);
			break;
		case packet:
			parsePacket(attributes);
			break;
		case brief:
			parseBrief(attributes);
			break;
		case proto:
			parseProto(attributes);
			break;
		case field:
			level++;
			parseField(attributes);
			break;
		case Frame:
			hasText = true;
			nodeText = new StringBuilder();
			parseFrame(attributes);
			break;
		case Color:
			parseColor(attributes);
			break;
		case Interface:
			parseInterface(attributes);
			break;
		case Direction:
			parseDirection(attributes);
			break;
		case Binary:
			parseBinary(attributes);
			break;
		default:
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		switch (Node.toNode(qName)) {
		case pdml:
			break;
		case packet:
			break;
		case brief:
			break;
		case proto:
			break;
		case field:
			level--;
			break;
		case Frame:
			hasText = false;
			frame.setValue(nodeText.toString().trim());
			break;
		case Color:
			break;
		case Interface:
			break;
		case Direction:
			break;
		case Binary:
			break;
		default:
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (hasText) {
			nodeText.append(new String(ch, start, length));
		}
	}

	private void parseBinary(Attributes attributes) {
		Binary binary = new Binary();
		binary.setBid(getIntValue(attributes, Attr.bid));
		packet.setBinary(binary);
	}

	private void parseDirection(Attributes attributes) {
		Direction direction = new Direction();
		direction.setDirection(getIntValue(attributes, Attr.direction));
		packet.setDirection(direction);
	}

	private void parseInterface(Attributes attributes) {
		Interface intf = new Interface();
		intf.setIntf(getIntValue(attributes, Attr.intf));
		packet.setIntface(intf);
	}

	private void parseColor(Attributes attributes) {
		Color color = new Color();
		color.setColor(getIntValue(attributes, Attr.color));
		packet.setColor(color);
	}


	private void parseFrame(Attributes attributes) {
		frame = new Frame();
		frame.setLen(getIntValue(attributes, Attr.len));
		packet.setFrame(frame);
	}

	private void parseField(Attributes attributes) {
		Field field = new Field();
		if (level == 1) {
			proto.addField(field);
			fieldMap.clear();
			fieldMap.put(level, field);
		} else {
			fieldMap.get(level - 1).addField(field);
			fieldMap.put(level, field);
		}

		field.setName(getValue(attributes, Attr.name));
		field.setShowName(getValue(attributes, Attr.showname));
		field.setSize(getIntValue(attributes, Attr.size));
		field.setPos(getIntValue(attributes, Attr.pos));
	}


	private void parseProto(Attributes attributes) {
		proto = new Proto();
		proto.setName(getValue(attributes, Attr.name));
		proto.setShowName(getValue(attributes, Attr.showname));
		proto.setSize(getIntValue(attributes, Attr.size));
		proto.setPos(getIntValue(attributes, Attr.pos));
		packet.addProto(proto);
	}


	private void parseBrief(Attributes attributes) {
		Brief brief = new Brief();
		brief.setTime(getValue(attributes, Attr.time));
		brief.setSrc(getValue(attributes, Attr.src));
		brief.setDst(getValue(attributes, Attr.dst));
		brief.setProtocol(getValue(attributes, Attr.protocol));
		brief.setLength(getIntValue(attributes, Attr.length));
		brief.setInfo(getValue(attributes, Attr.info));
		packet.setBrief(brief);
	}



	private Packet packet;
	private void parsePacket(Attributes attributes) {
		packet = new Packet();
		packet.setId(Integer.parseInt(attributes.getValue("id")));
		pdml.addPacket(packet);
	}


	private void parsePdml(Attributes attributes) {
		pdml = new Pdml();
		pdml.setVersion(getValue(attributes, Attr.version));
		pdml.setCreator(getValue(attributes, Attr.creator));
		pdml.setTime(getValue(attributes, Attr.time));
		pdml.setCaptureFile(getValue(attributes, Attr.capture_file));
	}


	private String getValue(Attributes attributes, Attr attr) {
		return attributes.getValue(attr.name());
	}

	private int getIntValue(Attributes attributes, Attr attr) {
		String value = getValue(attributes, attr);
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
		}
		return 0;
	}

}
