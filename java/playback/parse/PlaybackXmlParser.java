package com.eflag.ocloud3.playback.parse;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

public class PlaybackXmlParser {

	private String xmlPath;
	public PlaybackXmlParser(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	
	public void parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser parser = factory.newSAXParser();
			PlaybackXmlHandler handler = new PlaybackXmlHandler();
			parser.parse(new InputSource(xmlPath), handler);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	public static void main(String[] args) {
		PlaybackXmlParser parser = new PlaybackXmlParser("/media/49070b14-2de9-487f-a308-5ae2a3cbec80/tiny/git/playback/4966.xml");
		parser.parse();
	}
}
