package model;

public class SteamGame {

	private int _id;
	private String _name;
	private String _logoUrlString;
	private String _storeUrlString;
	
	public SteamGame(int id, String name, String logoUrl, String storeUrl) {
		_id = id;
		_name = name;
		_logoUrlString = logoUrl;
		_storeUrlString = storeUrl;
	}
	
	public int getId() {
		// TODO Auto-generated method stub
		return _id;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return _name;
	}

	public String getLogoUrl() {
		// TODO Auto-generated method stub
		return _logoUrlString;
	}

	public String getStoreUrl() {
		// TODO Auto-generated method stub
		return _storeUrlString;
	}
}
