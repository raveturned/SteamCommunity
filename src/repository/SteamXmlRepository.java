package repository;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SteamXmlRepository {

	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		//System.err.println("Element name: " + ele.getNodeName());
		//System.err.println("Tag name: " + tagName);
		//System.err.println("Textval found: " + textVal);


		return textVal;
	}

	public SteamProfile resolveSteamProfile(String uri)
	{
		return resolveSteamProfile(uri, DocumentBuilderFactory.newInstance());
	}

	public SteamProfile resolveSteamProfile(String uri, DocumentBuilderFactory dbf)	
	{
		Document result = null;
		SteamProfile profile = null;
		//get the factory
		
		try {
	
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
	
			//parse using builder to get DOM representation of the XML file
			result = db.parse(uri);
	
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();		
		}
		
		if (result != null)
		{
			Element root = result.getDocumentElement();
			//should be 'profile'
			//System.err.println("Profile root node name: " + root.getNodeName());
			
			if ("profile".equalsIgnoreCase(root.getNodeName()))
			{
				long id = Long.parseLong(getTextValue(root,"steamID64"));
				String name = getTextValue(root, "steamID");
				
				profile = new SteamProfile(id, name);	
			}
		}
		return profile;
	}
	
}
