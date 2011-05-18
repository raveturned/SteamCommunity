package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SteamProfileTests {

	 @Test
	 public void testBuild()
	 {
		 SteamProfile user = new SteamProfile(-1, "test");
		 assertNotNull("User should not be null", user);
		 assertEquals("Id should be value specified at creation", -1, user.getId());
		 assertEquals("Name should be value specified at creation", "test", user.getName());
	 }
	
}
