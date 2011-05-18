package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SteamGameTests {

	 @Test
	 public void testBuild()
	 {
		 SteamGame game = new SteamGame(-1, "test");
		 assertNotNull("Game should not be null", game);
		 assertEquals("Id should be value specified at creation", -1, game.getId());
		 assertEquals("Name should be value specified at creation", "test", game.getName());
	 }
}
