package repository;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.SteamGame;
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

	
	public static SteamProfile mapXmlToProfile(Element ele)
	{
		String profileErr = "Error resolving profile game: %s - %s";
		
		SteamProfile profile = null;
		if ("profile".equalsIgnoreCase(ele.getNodeName()))
		{
			try {
			long id = Long.parseLong(getTextValue(ele,"steamID64"));
			String name = getTextValue(ele, "steamID");
			
			profile = new SteamProfile(id, name);
			}
			catch(NumberFormatException nfe)
			{
				System.err.println(String.format(profileErr, nfe.getClass(), nfe.getLocalizedMessage()));						
			}
		}
		return profile;

	}
	
	
	public static ArrayList<SteamGame> mapXmlToGamesList(Element ele)
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
	
	public static SteamGame mapXmlToGame(Element ele)
	{
		SteamGame game = null;
		String gameErr = "Error resolving steam game: %s - %s";

		if ("game".equalsIgnoreCase(ele.getNodeName()))
		{

			try {
				int id = Integer.parseInt(getTextValue(ele, "appID"));
				String name = getTextValue(ele, "name");
				
				game = new SteamGame(id, name);
			}
			catch (NumberFormatException nfe)
			{
				System.err.println(String.format(gameErr, nfe.getClass(), nfe.getLocalizedMessage()));						
			}
		}
		
	return game;
	}

	
}
