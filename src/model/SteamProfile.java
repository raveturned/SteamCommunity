package model;


public class SteamProfile {

	long _id;
	String _name;
	
	public SteamProfile(long id, String name) {
		_id = id;
		_name = name;
	}

	public long getId() {
		return _id;
	}

	public String getName() {
		return _name;
	}
			
}
