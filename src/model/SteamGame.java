package model;

public class SteamGame {

	private int _id;
	private String _name;
	private String _logoUrlString;
	private String _storeUrlString;
	private float _hoursOnRecord;
	
	public SteamGame(int id, String name, String logoUrl, String storeUrl, float hoursOnRecord) {
		_id = id;
		_name = name;
		_logoUrlString = logoUrl;
		_storeUrlString = storeUrl;
		_hoursOnRecord = hoursOnRecord;
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

	public float getHoursOnRecord() {
		// TODO Auto-generated method stub
		return _hoursOnRecord;
	}
}
