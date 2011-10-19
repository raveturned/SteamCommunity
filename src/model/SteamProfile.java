package model;

import java.util.Collection;
import java.util.HashMap;

public class SteamProfile {

	long _id;
	String _name;
	String _smallAvatarUrlString;
	String _mediumAvatarUrlString;
	String _largeAvatarUrlString;
	
	HashMap<Integer, SteamProfileGameData> _profileGameData = new HashMap<Integer, SteamProfileGameData>();

	
	public SteamProfile(long id, String name,
			String smallAvatarUrl, String mediumAvatarUrl, String largeAvatarUrl) {
		_id = id;
		_name = name;
		_smallAvatarUrlString = smallAvatarUrl;
		_mediumAvatarUrlString = mediumAvatarUrl;
		_largeAvatarUrlString = largeAvatarUrl;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
	
	public String getSmallAvatarUrl() {
		return _smallAvatarUrlString;
	}
	
	public String getMediumAvatarUrl() {
		return _mediumAvatarUrlString;
	}	
	
	public String getLargeAvatarUrl() {
		return _largeAvatarUrlString;
	}
	
	public String getCommunityProfileUrl()
	{
		return String.format("http://steamcommunity.com/profiles/%s/", _id);
	}
	
	public Collection<SteamProfileGameData> getAllProfileGameData()
	{
		return _profileGameData.values(); 
	}
	
	public void setProfileGameData(int appId, SteamProfileGameData gamedata)
	{
		_profileGameData.put(appId, gamedata); 
	}
	
	public SteamProfileGameData getProfileGameData(int appId)
	{
		if (_profileGameData.containsKey(appId))
		{
			return _profileGameData.get(appId);
		}
		else return null;
	}
	
}
