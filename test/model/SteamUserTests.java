package model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SteamUserTests {

	 @Test
	 public void testBuild()
	 {
		 SteamUser user = new SteamUser();
		 assertNotNull("User should not be null", user);
	 }
	
}
