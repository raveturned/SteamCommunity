package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class SteamGroupTests {

	 @Test
	 public void testBuild()
	 {
		 SteamGroup group = new SteamGroup(-1, "test");
		 assertNotNull("Group should not be null", group);
		 assertEquals("Id should be value specified at creation", -1, group.getId());
		 assertEquals("Name should be value specified at creation", "test", group.getName());
	 }
	
}
