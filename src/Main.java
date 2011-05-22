import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import repository.SteamXmlRepository;
import model.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SteamXmlRepository repo = new SteamXmlRepository();
		
		HashMap<Integer, SteamGame> gameIdDetailsMap = new HashMap<Integer, SteamGame>();
		HashMap<Integer, ArrayList<SteamProfile>> gameIdPlayersMap = new HashMap<Integer, ArrayList<SteamProfile>>();

		
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
		TreeMap<Integer, ArrayList<SteamGame>> playerCountGamesMap = new TreeMap<Integer, ArrayList<SteamGame>>();

		System.err.println("Sorting by player count...");

		for (Integer gameId : gameIdPlayersMap.keySet())
		{
			ArrayList<SteamProfile> profiles = gameIdPlayersMap.get(gameId);
			SteamGame gameDetail = gameIdDetailsMap.get(gameId);
			
			if (!playerCountGamesMap.containsKey(profiles.size()))
			{
				// add new list containing player, add player map 
				ArrayList<SteamGame> list = new ArrayList<SteamGame>();
				list.add(gameDetail);
				playerCountGamesMap.put(profiles.size(), list);
			}					
			else
			{
				//playerCountGamesMap.get(profiles.size());
				ArrayList<SteamGame> list = playerCountGamesMap.get(profiles.size());
				//add to list
				list.add(gameDetail);
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
		//logo, title, playercount, playerdatastring
		String output = "<tr><td width=200px><img src=\"%s\"/><br/>%s</td><td>%s</td><td>%s</td></tr>";
		// output sorted values - games, size, profiles (from earlier hashset)
		for (Integer playerCount : playerCountGamesMap.descendingKeySet())
		{
			
			//ignore games only owned by one person - useful?
			if (playerCount < 2)
				continue;
			
			for (SteamGame game : playerCountGamesMap.get(playerCount))
			{
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
								
				out.println(String.format(output, game.getLogoUrl(), game.getName(), playerCount, playerString ));
			}							
		}
		//html footer
		out.println("</table></body></html>");
		
		out.close();

	}
		
}
