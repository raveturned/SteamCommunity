package service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import service.SteamDataService;

public class SteamDataServiceTests {
	
	@Test
	public void testHasDetailsValidAppId()
	{
		//half-life 2
		int id = 220;
		
		SteamDataService svc = new SteamDataService();
				
		assertTrue("HL2 is single-player", svc.gameHasDetail(id, "single-player"));
		assertFalse("HL2 is not multi-player", svc.gameHasDetail(id, "multi-player"));

	}	


}
