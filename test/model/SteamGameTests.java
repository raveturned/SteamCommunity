package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SteamGameTests {

	 @Test
	 public void testBuild()
	 {
		 int testId = -1;
		 String testName = "test";
		 String testLogo = "logo";
		 String testStoreUrl = "store";
		 
		 SteamGame game = new SteamGame(testId, testName, testLogo, testStoreUrl);
		 assertNotNull("Game should not be null", game);
		 assertEquals("Id should be value specified at creation", testId, game.getId());
		 assertEquals("Name should be value specified at creation", testName, game.getName());
		 assertEquals("Logo should be value specified at creation", testLogo, game.getLogoUrl());
		 assertEquals("Store link should be value specified at creation", testStoreUrl, game.getStoreUrl());
	 }
}
