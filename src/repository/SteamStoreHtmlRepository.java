package repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.text.html.HTMLEditorKit;

public class SteamStoreHtmlRepository {

	String appUrl = "http://store.steampowered.com/app/%s/";
	
	public String[] getAppDetails(int id)
	{
		
	    SteamStoreHtmlParserProvider provider = new SteamStoreHtmlParserProvider();
	    HTMLEditorKit.Parser parser = provider.getParser();
	    SteamStoreCallback callback = new SteamStoreCallback();

		//get stream to html
	    try {
	        URL u = new URL(String.format(appUrl, id) );
	        InputStream in = u.openStream();
	        InputStreamReader r = new InputStreamReader(in);
	        parser.parse(r, callback, true);
	      } catch (IOException e) {
	        System.err.println(e);
	      }		//send through parser
	      return callback.getResults();
	}

	public boolean hasDetail(int id, String detail) {
		String[] details = getAppDetails(id);
		
		for (String s:details)
		{
			if (s.trim().equalsIgnoreCase(detail.trim()))
				return true;
		}
		
		return false;
	}
	
}
