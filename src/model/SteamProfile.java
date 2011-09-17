package model;

import java.util.HashMap;


public class SteamProfile {

	long _id;
	String _name;
	String _smallAvatarUrlString;
	String _mediumAvatarUrlString;
	String _largeAvatarUrlString;
	
	HashMap<Integer, Float> _appHoursOnRecord = new HashMap<Integer, Float>();
	
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
		// TODO Auto-generated method stub
		return _smallAvatarUrlString;
	}
	
	public String getMediumAvatarUrl() {
		// TODO Auto-generated method stub
		return _mediumAvatarUrlString;
	}	
	
	public String getLargeAvatarUrl() {
		// TODO Auto-generated method stub
		return _largeAvatarUrlString;
	}
	
	public String getCommunityProfileUrl()
	{
		return String.format("http://steamcommunity.com/profiles/%s/", _id);
	}
	
	public void setHoursOnRecord(int appId, float hours)
	{
		_appHoursOnRecord.put(appId, hours);
	}
	
	public float getHoursOnRecord(int appId)
	{
		if (_appHoursOnRecord.containsKey(appId))
		{
			return _appHoursOnRecord.get(appId);
		}
		else return 0f;
	}
}
