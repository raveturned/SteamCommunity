package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class SteamProfileTests {

	 @Test
	 public void testBuild()
	 {
		 long testId = -1l;
		 String testName = "test";
		 String smallAvatar = "small";
		 String mediumAvatar = "medium";
		 String largeAvatar = "large";
		 //long[] testGroupIds = {-10, -11, -12};
		 
		 SteamProfile user = new SteamProfile(testId, testName, smallAvatar, mediumAvatar, largeAvatar);
		 assertNotNull("User should not be null", user);
		 assertEquals("Id should be value specified at creation", testId, user.getId());
		 assertEquals("Name should be value specified at creation", testName, user.getName());
		 assertEquals("Small avatar should be value specified at creation", smallAvatar, user.getSmallAvatarUrl());
		 assertEquals("Medium avatar should be value specified at creation", mediumAvatar, user.getMediumAvatarUrl());
		 assertEquals("Large avatar should be value specified at creation", largeAvatar, user.getLargeAvatarUrl());
		 //assertEquals("GroupIds should be correct length", 3, user.getGroupIds().length);
	 }
	
}
