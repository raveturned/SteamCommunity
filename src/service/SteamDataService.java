package service;

import java.util.HashMap;

import model.SteamGame;
import model.SteamProfile;
import repository.SteamStoreHtmlRepository;
import repository.SteamXmlRepository;

public class SteamDataService {
	
	SteamXmlRepository xmlRepo = null;
	SteamStoreHtmlRepository storeRepo = null;
	
	HashMap<Integer, String[]> gameDetails = new HashMap<Integer, String[]>();
	HashMap<Integer, SteamGame> freeGames = new HashMap<Integer, SteamGame>();
	//HashMap<Integer, >
	
	public SteamDataService()
	{
		xmlRepo = new SteamXmlRepository();
		storeRepo = new SteamStoreHtmlRepository();
		
		initFreeGames();
	}
	
	//ToDo: find a way to not hard-code this
	private void initFreeGames()
	{
		addFreeGame(630,	"Alien Swarm");
		addFreeGame(102700,	"Alliance of Valliant Arms");
		addFreeGame(9880,	"Champions Online");
		addFreeGame(36620,	"Forsaken World");
		addFreeGame(17020,	"Global Agenda");
		addFreeGame(39000,	"Moonbase Alpha");
		addFreeGame(99900,	"Spiral Knights");
		addFreeGame(440,	"Team Fortress 2");
		addFreeGame(11020,	"TrackMania Nations Forever");
		addFreeGame(107900, "War Inc. Battlezone");
	}
	
	private SteamGame mockGame(int id, String name)
	{
		String storeUrl = String.format("http://store.steampowered.com/app/%s", id);
		return new SteamGame(id, name, null, storeUrl, 0.0f);
	}
	private void addFreeGame(int id, String name)
	{
		freeGames.put(id, mockGame(id, name));
	}

	public long[] getSteamGroupMembers(String group) {
		// TODO Auto-generated method stub
		return xmlRepo.getSteamGroupMembers(group);
	}

	public SteamProfile getSteamProfile(long id) {
		// TODO Auto-generated method stub
		return xmlRepo.getSteamProfile(id);
	}

	public SteamGame[] getSteamGamesById(long id) {
		SteamGame[] games = xmlRepo.getSteamGamesById(id);		
		// TODO Auto-generated method stub
		return games;
	}

	public boolean gameHasDetail(int id, String detail) {
		String[] details;
		if (gameDetails.containsKey(id))
		{
			details = gameDetails.get(id);
		}
		else
		{
			details = storeRepo.getAppDetails(id);
			gameDetails.put(id, details);
		}
		

		for (String s:details)
		{
			if (s.trim().equalsIgnoreCase(detail.trim()))
				return true;
		}
		
		return false;
	}
	
	public boolean isFree(SteamGame game)
	{
		boolean isFree = freeGames.containsKey(game.getId());
		//if (isFree) System.err.println(String.format("%s is a free game!", game.getName()));	// debug
		return isFree;
	}

	public boolean isMultiplayer(SteamGame game) {
		String[] mpStrings = {"multi-player","co-op","cross-platform multiplayer"};
		
		String[] details;
		if (gameDetails.containsKey(game.getId()))
		{
			details = gameDetails.get(game.getId());
		}
		else
		{
			System.err.println(String.format("Getting details for %s...", game.getName()));
			details = storeRepo.getAppDetails(game.getId());
			gameDetails.put(game.getId(), details);
		}
		
		for (String i :details)
		{
			for (String j: mpStrings)
			{
				if (i.trim().equalsIgnoreCase(j.trim()))
					return true;
			}
		}
		return false;
	}

	public SteamGame[] getFreeGames() {
	    return freeGames.values().toArray(new SteamGame[freeGames.size()]);
	}
	
	
}
