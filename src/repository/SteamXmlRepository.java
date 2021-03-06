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
	
	XmlSteamMapper mapper = null;
	
	public SteamXmlRepository()
	{
		mapper = new XmlSteamMapper();
	}
	
	private Document getXmlDoc(String uri, DocumentBuilderFactory dbf) {
		Document result = null;
		String generalErr = "Error resolving uri: %s - %s";
		
		//get the factory
		try {
	
			result = getXmlDocInner(uri, dbf);
	
		}catch(ParserConfigurationException pce) {
			//pce.printStackTrace();
			//TODO: log exception
			System.err.println(String.format(generalErr, pce.getClass(), pce.getLocalizedMessage()));
		}catch(SAXException se) {
			//se.printStackTrace();
			//TODO: log exception
			System.err.println(String.format(generalErr, se.getClass(), se.getLocalizedMessage()));
		}catch(IOException ioe) {
			//try again...
			try {
				result = getXmlDocInner(uri, dbf);
			}
			catch(Exception ioe2)
			{
				System.err.println(String.format(generalErr, ioe2.getClass(), ioe2.getLocalizedMessage()));
			}
		}
		return result;
	}

	private Document getXmlDocInner(String uri, DocumentBuilderFactory dbf)
			throws ParserConfigurationException, SAXException, IOException {
		Document result;
		//Using factory get an instance of document builder
		DocumentBuilder db = dbf.newDocumentBuilder();

		//parse using builder to get DOM representation of the XML file
		result = db.parse(uri);
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
			
			profile = mapper.mapXmlToProfile(root);
			
		}
		return profile;
	}

	public SteamProfileGameData[] resolveSteamGames(String uri)
	{
		return resolveSteamGames(uri, DocumentBuilderFactory.newInstance());
	}
	
	public SteamProfileGameData[] resolveSteamGames(String uri, DocumentBuilderFactory dbf)	
	{
		ArrayList<SteamProfileGameData> gameList = new ArrayList<SteamProfileGameData>();

		Document result = getXmlDoc(uri, dbf);
		
		
		if (result != null)
		{
			Element root = result.getDocumentElement();
			//should be 'profile'
			//System.err.println("Profile root node name: " + root.getNodeName());
			gameList.addAll(mapper.mapXmlToProfileGameDataList(root));
			
		}
		return gameList.toArray(new SteamProfileGameData[gameList.size()]);
	}

	public SteamProfileGameData[] getSteamGamesById(long id)
	{
		String uri = String.format("http://www.steamcommunity.com/profiles/%s/games?xml=1", id);
		return resolveSteamGames(uri);
	}

	public SteamProfileGameData[] getSteamGamesByName(String name)
	{
		String uri = String.format("http://www.steamcommunity.com/id/%s/games?xml=1", name);
		return resolveSteamGames(uri);
	}	

	
	public long[] getSteamGroupMembers(SteamGroup id)
	{
		String uri = String.format("http://steamcommunity.com/gid/%s/memberslistxml/?xml=1", id);
		return resolveSteamGroupMembers(uri);
	}

	public long[] getSteamGroupMembers(String name)
	{
		String uri = String.format("http://steamcommunity.com/groups/%s/memberslistxml/?xml=1", name);
		return resolveSteamGroupMembers(uri);
	}
	
	public long[] resolveSteamGroupMembers(String uri)
	{
		return resolveSteamGroupMembers(uri, DocumentBuilderFactory.newInstance());
	}

	public long[] resolveSteamGroupMembers(String uri, DocumentBuilderFactory dbf)	
	{
		ArrayList<Long> memberIds = new ArrayList<Long>();
		
		while (uri != null)
		{
			Document result = getXmlDoc(uri, dbf);
			
			if (result != null)
			{
				Element root = result.getDocumentElement();
				
				// get members
				// get if more pages
				
				// list += mappergetids
				memberIds.addAll(mapper.mapXmlToMemberList(root));
				uri = mapper.xmlGetMemberListNextPage(root);			
			}
			else
			{
				//in case result is null (protective)
				uri = null;
			}
			
		}
				
	    long[] ret = new long[memberIds.size()];
	    int i = 0;
	    for (Long val: memberIds)
	    {
	        ret[i] = val;
	        i++;
	    }
	    return ret;
	}


}
