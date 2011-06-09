package repository;

import static org.junit.Assert.*;

import org.junit.Test;

public class SteamStoreHtmlRepositoryTests {

	@Test
	public void testAppDetailsValidAppId()
	{
		//half-life 2
		int id = 220;
		SteamStoreHtmlRepository repo = new SteamStoreHtmlRepository();
		
		String[] details = repo.getAppDetails(id);
		
		assertNotNull("Details array should not be null", details);
		assertNotSame("Details array should not be empty", 0, details.length);

		/*
		System.err.println("Details:");
		
		for (String s : details)
		{
			System.err.println(s);
		}
		*/
		
		assertEquals("Details array should have 7 entries", 7, details.length);
		

	}
		
}
