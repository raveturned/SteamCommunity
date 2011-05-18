package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SteamProfileTests {

	 @Test
	 public void testBuild()
	 {
		 long testId = -1l;
		 String testName = "test";
		 //long[] testGroupIds = {-10, -11, -12};
		 
		 SteamProfile user = new SteamProfile(testId, testName /*, testGroupIds*/);
		 assertNotNull("User should not be null", user);
		 assertEquals("Id should be value specified at creation", testId, user.getId());
		 assertEquals("Name should be value specified at creation", testName, user.getName());
		 //assertEquals("GroupIds should be correct length", 3, user.getGroupIds().length);
	 }
	
}
