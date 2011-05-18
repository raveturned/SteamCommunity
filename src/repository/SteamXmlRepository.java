package repository;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.*;

import org.w3c.dom.*;
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
	
	private Document getXmlDoc(String uri, DocumentBuilderFactory dbf) {
		Document result = null;
		String generalErr = "Error resolving uri: %s - %s";
		
		//get the factory
		try {
	
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
	
			//parse using builder to get DOM representation of the XML file
			result = db.parse(uri);
	
		}catch(ParserConfigurationException pce) {
			//pce.printStackTrace();
			//TODO: log exception
			System.err.println(String.format(generalErr, pce.getClass(), pce.getLocalizedMessage()));
		}catch(SAXException se) {
			//se.printStackTrace();
			//TODO: log exception
			System.err.println(String.format(generalErr, se.getClass(), se.getLocalizedMessage()));
		}catch(IOException ioe) {
			//ioe.printStackTrace();
			//TODO: log exception
			System.err.println(String.format(generalErr, ioe.getClass(), ioe.getLocalizedMessage()));
		}
		return result;
	}
	
	public SteamProfile getSteamProfile(long id)
	{
		String uri = String.format("http://www.steamcommunity.com/profiles/%s?xml=1", id);
		return resolveSteamProfile(uri);
	}

	public SteamProfile getSteamProfile(String name)
	{
		String uri = String.format("http://www.steamcommunity.com/id/%s?xml=1", name);
		return resolveSteamProfile(uri);
	}
	
	public SteamProfile resolveSteamProfile(String uri)
	{
		return resolveSteamProfile(uri, DocumentBuilderFactory.newInstance());
	}

	public SteamProfile resolveSteamProfile(String uri, DocumentBuilderFactory dbf)	
	{
		SteamProfile profile = null;

		Document result = getXmlDoc(uri, dbf);
		
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

	public SteamGame[] resolveSteamGames(String uri)
	{
		return resolveSteamGames(uri, DocumentBuilderFactory.newInstance());
	}
	
	public SteamGame[] resolveSteamGames(String uri, DocumentBuilderFactory dbf)	
	{
		String gameErr = "Error resolving steam game: %s - %s";
		ArrayList<SteamGame> gameList = new ArrayList<SteamGame>();

		Document result = getXmlDoc(uri, dbf);
		
		if (result != null)
		{
			Element root = result.getDocumentElement();
			//should be 'profile'
			//System.err.println("Profile root node name: " + root.getNodeName());
			
			if ("gamesList".equalsIgnoreCase(root.getNodeName()))
			{
				
				NodeList nl = root.getElementsByTagName("game");
				
				int i = 0;
				Node node = nl.item(0);
				
				while(node != null){
										
					try{
					Element el = (Element)node;
					int id = Integer.parseInt(getTextValue(el, "appID"));
					String name = getTextValue(el, "name");
					
					SteamGame game = new SteamGame(id, name);
					gameList.add(game);
					}
					catch (NumberFormatException nfe)
					{
						System.err.println(String.format(gameErr, nfe.getClass(), nfe.getLocalizedMessage()));						
					}
					
					i++;
					node = nl.item(i);
				}				

			}
		}
		return gameList.toArray(new SteamGame[gameList.size()]);
	}

	public SteamGame[] getSteamGamesById(long id)
	{
		String uri = String.format("http://www.steamcommunity.com/profiles/%s/games?xml=1", id);
		return resolveSteamGames(uri);
	}

	public SteamGame[] getSteamGamesByName(String name)
	{
		String uri = String.format("http://www.steamcommunity.com/id/%s/games?xml=1", name);
		return resolveSteamGames(uri);
	}	
	
}
