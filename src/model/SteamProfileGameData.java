package model;

public class SteamProfileGameData {

	//private long _profileId;
	private SteamGame _app;
	
	private float _hoursOnRecord;
	private float _hoursInLast2Weeks;
	
	
	public SteamProfileGameData(SteamGame app, float hrsOnRecord, float hrsInLast2Weeks)
	{
		_app = app;
		_hoursOnRecord = hrsOnRecord;
		_hoursInLast2Weeks = hrsInLast2Weeks;
	}
	
	public SteamGame get_app() {
		return _app;
	}

	public float get_hoursOnRecord() {
		return _hoursOnRecord;
	}


	public float get_hoursInLast2Weeks() {
		return _hoursInLast2Weeks;
	}

	
}
