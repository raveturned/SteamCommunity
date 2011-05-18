package model;

import java.io.*;
import java.net.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class SteamProfile {

	long _id;
	String _name;
	
	public SteamProfile(long id, String name) {
		_id = id;
		_name = name;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
	
}
