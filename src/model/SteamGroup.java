package model;

public class SteamGroup {

	private long _id;
	private String _name;

	public SteamGroup(long id, String name) {
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
