package model;


public class SteamProfile {

	long _id;
	String _name;
	//long[] _groupIds;
	
	public SteamProfile(long id, String name) {
		_id = id;
		_name = name;
		//_groupIds = groupIds;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
	
	/*
	public long[] getGroupIds() {
		return _groupIds;
	}
	*/	
}
