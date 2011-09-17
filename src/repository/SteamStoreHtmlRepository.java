package repository;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
	        InputStreamReader r = getStreamReaderForUrl(id);
	        parser.parse(r, callback, true);
	    }
	    catch (IOException e) {
	    	System.err.print(" (*FAIL - 2nd attempt...*) ");
	    	//try again...
	  	    try {
		        InputStreamReader r = getStreamReaderForUrl(id);
		        parser.parse(r, callback, true);
		    }
	  	    catch (IOException e2) {
	  	    	//report error
		        System.err.println(e2);
		    }
	    }		//send through parser
	    return callback.getResults();
	}

	private InputStreamReader getStreamReaderForUrl(int id)
			throws MalformedURLException, IOException {
		URL u = new URL(String.format(appUrl, id) );
		InputStream in = u.openStream();
		InputStreamReader r = new InputStreamReader(in);
		return r;
	}	
}
