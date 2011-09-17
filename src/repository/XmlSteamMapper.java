package repository;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.SteamGame;
import model.SteamGroup;
import model.SteamProfile;

public class XmlSteamMapper {

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
	
	public SteamProfile mapXmlToProfile(Element ele)
	{
		String profileErr = "Error resolving steam profile: %s - %s";
		
		SteamProfile profile = null;
		if ("profile".equalsIgnoreCase(ele.getNodeName()))
		{
			try {
			long id = Long.parseLong(getTextValue(ele,"steamID64"));
			String name = getTextValue(ele, "steamID");
			String smallAvatarUrl = getTextValue(ele, "avatarIcon");
			String mediumAvatarUrl = getTextValue(ele, "avatarMedium");
			String largeAvatarUrl = getTextValue(ele, "avatarFull");
			
			profile = new SteamProfile(id, name, smallAvatarUrl, mediumAvatarUrl, largeAvatarUrl);
			}
			catch(NumberFormatException nfe)
			{
				System.err.println(String.format(profileErr, nfe.getClass(), nfe.getLocalizedMessage()));						
			}
		}
		return profile;

	}
	
	public ArrayList<SteamGame> mapXmlToGamesList(Element ele)
	{
		ArrayList<SteamGame> gameList = new ArrayList<SteamGame>();
		
		if ("gamesList".equalsIgnoreCase(ele.getNodeName()))
		{
			
			NodeList nl = ele.getElementsByTagName("game");
			
			int i = 0;
			Node node = nl.item(0);
			
			while(node != null){
									
				Element el = (Element)node;

				SteamGame game = mapXmlToGame(el);
				
				if (game != null)
				{
					gameList.add(game);
				}
				
				i++;
				node = nl.item(i);
			}
		}
		return gameList;

	}
	
	public SteamGame mapXmlToGame(Element ele)
	{
		SteamGame game = null;
		String gameErr = "Error resolving steam game: %s - %s";

		if ("game".equalsIgnoreCase(ele.getNodeName()))
		{

			try {
				int id = Integer.parseInt(getTextValue(ele, "appID"));
				String name = getTextValue(ele, "name");
				String logoUrl = getTextValue(ele, "logo");
				String storeUrl = getTextValue(ele, "storeLink");
				
				String hoursOnRecordStr = getTextValue(ele, "hoursOnRecord");
				float hoursOnRecord = 0.0f;
				if (!(hoursOnRecordStr == null || hoursOnRecordStr.isEmpty()))
				{
					hoursOnRecord = Float.parseFloat(hoursOnRecordStr);
				}
				
				game = new SteamGame(id, name, logoUrl, storeUrl, hoursOnRecord);
			}
			catch (NumberFormatException nfe)
			{
				System.err.println(String.format(gameErr, nfe.getClass(), nfe.getLocalizedMessage()));						
			}
		}
		
	return game;
	}

	public ArrayList<SteamGroup> mapXmlToGroupList(Element ele)
	{
		ArrayList<SteamGroup> groupList = new ArrayList<SteamGroup>();
		
		if ("groups".equalsIgnoreCase(ele.getNodeName()))
		{
			
			NodeList nl = ele.getElementsByTagName("group");
			
			int i = 0;
			Node node = nl.item(0);
			
			while(node != null){
									
				Element el = (Element)node;

				SteamGroup group = mapXmlToGroup(el);
				
				if (group != null)
				{
					groupList.add(group);
				}
				
				i++;
				node = nl.item(i);
			}
		}
		return groupList;

	}
	
	public SteamGroup mapXmlToGroup(Element ele)
	{
		SteamGroup game = null;
		String groupErr = "Error resolving steam group: %s - %s";

		if ("group".equalsIgnoreCase(ele.getNodeName()))
		{

			try {
				long id = Long.parseLong(getTextValue(ele, "groupID64"));
				String name = getTextValue(ele, "groupName");
				
				game = new SteamGroup(id, name);
			}
			catch (NumberFormatException nfe)
			{
				System.err.println(String.format(groupErr, nfe.getClass(), nfe.getLocalizedMessage()));						
			}
		}
		
	return game;
	}

	public ArrayList<Long> mapXmlToMemberList(Element ele) {

		ArrayList<Long> memberIds = new ArrayList<Long>();
		
		if ("memberList".equalsIgnoreCase(ele.getNodeName()))
		{
			
			NodeList nl = ele.getElementsByTagName("steamID64");
			
			int i = 0;
			Node node = nl.item(0);
			
			while(node != null){
									
				Element el = (Element)node;

				Long id = Long.parseLong(el.getFirstChild().getNodeValue());
				
				memberIds.add(id);
				i++;
				node = nl.item(i);
			}
		}
		return memberIds;

	}
	
	public String xmlGetMemberListNextPage(Element ele) {
		String pageLink = null;
		
		if ("memberList".equalsIgnoreCase(ele.getNodeName()))
		{			
			NodeList nl = ele.getElementsByTagName("nextPageLink");
			
			if (nl != null && nl.getLength() > 0)
			{
			Node node = nl.item(0);
			pageLink = ((Element)node).getFirstChild().getNodeValue();
			}
			
		}
		return pageLink;

	}
	
	
}
