package model;

import java.util.ArrayList;
import java.util.List;

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
		return _id;
	}

	public String getName() {
		return _name;
	}

	public String getLogoUrl() {
		return _logoUrlString;
	}

	public String getStoreUrl() {
		return _storeUrlString;
	}
}
