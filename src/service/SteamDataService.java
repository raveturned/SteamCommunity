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
	
	public SteamDataService()
	{
		xmlRepo = new SteamXmlRepository();
		storeRepo = new SteamStoreHtmlRepository();
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
		// TODO Auto-generated method stub
		return xmlRepo.getSteamGamesById(id);
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

	
	
}
