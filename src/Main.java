import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import repository.SteamStoreHtmlRepository;
import repository.SteamXmlRepository;
import model.*;

// TODO: should not call repo directly - all through service layer
// service layer should call repo, cache objects (e.g. game), perform sorting
// output should be mediated by separate view class

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		
		// TODO get ids from group xml page
		//get profiles for all people from member list
		long[] memberIds = {
				// list of as 20/05/2011
				76561197970485997l,
				76561198006375606l,
				76561198030489958l,
				76561197969494863l,
				76561197964551997l
				};
		//for each profile, get all games
		
		//repository
		SteamXmlRepository repo = new SteamXmlRepository();
		//map of game id to game details
		HashMap<Integer, SteamGame> gameIdDetailsMap = new HashMap<Integer, SteamGame>();
		//map of game id to players
		HashMap<Integer, ArrayList<SteamProfile>> gameIdPlayersMap = new HashMap<Integer, ArrayList<SteamProfile>>();

		//maintain hashtable of game id, games
		//maintain hashtable of game id, list of profiles
		for (long id :memberIds)
		{	
			System.err.print(String.format("Getting profile for id %s", id));
			SteamProfile profile = repo.getSteamProfile(id);
			System.err.println(String.format(" %s", profile.getName()));
			if (profile != null)
			{
				SteamGame[] games= repo.getSteamGamesById(id);
				
				for (SteamGame game : games)
				{
					if (!gameIdDetailsMap.containsKey(game.getId()))
					{
						//add game to details map
						gameIdDetailsMap.put(game.getId(), game);
						// add new list containing player, add player map 
						ArrayList<SteamProfile> list = new ArrayList<SteamProfile>();
						list.add(profile);
						gameIdPlayersMap.put(game.getId(), list);
					}					
					else
					{
						// game details should be in map
						//gameIdDetailsMap.get(game.getId());
						ArrayList<SteamProfile> list = gameIdPlayersMap.get(game.getId());
						//add to list
						list.add(profile);
					}
				}				
			}
		}
		
		
		//create new sorted hashtable of size of profile and game
		TreeMap<Integer, SortedMap<String, SteamGame>> playerCountGamesMap = new TreeMap<Integer, SortedMap<String, SteamGame>>();

		System.err.println("Sorting by player count...");

		for (Integer gameId : gameIdPlayersMap.keySet())
		{
			ArrayList<SteamProfile> profiles = gameIdPlayersMap.get(gameId);
			SteamGame gameDetail = gameIdDetailsMap.get(gameId);
			
			if (!playerCountGamesMap.containsKey(profiles.size()))
			{
				// add new list containing player, add player map 
				SortedMap<String, SteamGame> map = new TreeMap<String, SteamGame>();
				map.put(gameDetail.getName(), gameDetail);
				playerCountGamesMap.put(profiles.size(), map);
			}					
			else
			{
				//playerCountGamesMap.get(profiles.size());
				SortedMap<String, SteamGame> map = playerCountGamesMap.get(profiles.size());
				//add to list
				map.put(gameDetail.getName(), gameDetail);
			}
		}				

	    // Create file
		
		PrintStream out = null;
		try {
			out = new PrintStream(new FileOutputStream("output.html", false));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out = System.out;
			out.println();
			out.println("COULD NOT CREATE FILE - fallback to console output");
			out.println("--------------------------------------------------");
			out.println();
		}
		//html headers
		out.println("<html><head></head><body><table border=1px>");
		
		out.println("<tr><th width=200px>Game</th><th>Player Count</th><th>Players</th></tr>");
		
		//System.err.println("Enumerating games:");
		//gamedatastring, playercount, playerdatastring
		String output = "<tr><td width=200px>%s</td><td>%s</td><td>%s</td></tr>";
		// output sorted values - games, size, profiles (from earlier hashset)
		SteamStoreHtmlRepository storeRepo = new SteamStoreHtmlRepository();
		for (Integer playerCount : playerCountGamesMap.descendingKeySet())
		{
			//ignore games only owned by one person - useful?
			if (playerCount < 2)
				continue;
			
			SortedMap<String, SteamGame> map = playerCountGamesMap.get(playerCount);
			
			for (SteamGame game : map.values())
			{
				System.err.print(String.format("Game '%s' (id:%s)", game.getName(), game.getId()));
				

				if (!storeRepo.hasDetail(game.getId(), "Multi-player"))
				{
					System.err.println(" not multi-player.");
					continue;
				}

				System.err.println(" is multi-player! Sending output.");
				
				String gamedata = formatGameData(game);
				
				ArrayList<SteamProfile> players = gameIdPlayersMap.get(game.getId());
				String playerString = "";
				
				for ( int i = 0; i < players.size(); i++)
				{
					SteamProfile player = players.get(i);
					playerString += String.format("<img src=\"%s\"/> %s", player.getSmallAvatarUrl(), player.getName());
					if (i < players.size() -1)
					{
						playerString += "<br/>";
					}
				}
								
				out.println(String.format(output, gamedata, playerCount, playerString ));
			}							
		}
		//html footer
		DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG);
		String dateinfo = formatter.format(new Date());
		out.println(String.format("</table><br/>Last generated: %s</body></html>", dateinfo));
		
		out.close();

	}

	private static String formatGameData(SteamGame game) {
		String format = "<a href=\"%s\"><img src=\"%s\"/><br/>%s</a>";
		String output = String.format(format, game.getStoreUrl(), game.getLogoUrl(), game.getName());
		return output;
	}
		
}
