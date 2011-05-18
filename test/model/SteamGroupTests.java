package model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SteamGroupTests {

	 @Test
	 public void testBuild()
	 {
		 SteamGroup group = new SteamGroup();
		 assertNotNull("Group should not be null", group);
	 }
	
}
