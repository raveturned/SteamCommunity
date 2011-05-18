package model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SteamGameTests {

	 @Test
	 public void testBuild()
	 {
		 SteamGame game = new SteamGame();
		 assertNotNull("Game should not be null", game);
	 }
}
