package repository;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import model.*;

import org.junit.Ignore;
import org.junit.Test;

public class SteamXmlRepositoryTests {


	@Test @Ignore("Malformed XML? Needs fix.")
	public void testProbemProfile()
	{
		SteamXmlRepository repo = new SteamXmlRepository();
		long id = 76561198024616768l;
		
		SteamProfile profile = repo.getSteamProfile(id);
		assertNotNull("Profile should not be null", profile);
		assertEquals("Id should be as supplied", id, profile.getId());
		assertNotNull("Name should not be null", profile.getName());
		assertNotNull("Small avatar should not be null", profile.getSmallAvatarUrl());
		assertNotNull("Medium avatar should not be null", profile.getMediumAvatarUrl());
		assertNotNull("Large avatar should not be null", profile.getLargeAvatarUrl());
		
		
	}
	
	@Test
	public void testValidUri()
	{
		String uri = "http://www.steamcommunity.com/id/raveturned?xml=1";
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.resolveSteamProfile(uri);
		
		assertNotNull("Profile should not be null", profile);
		assertEquals("Id should be value specified at creation", 76561197970485997l, profile.getId());
		assertTrue("Name should be value specified at creation", "raveturned".equalsIgnoreCase(profile.getName()));

	}
	
	@Test
	public void testMalformedUri()
	{
		String uri = "hrrp://www.steamcommunity.com/id/raveturned?xml=1";
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.resolveSteamProfile(uri);
		
		assertNull("Profile should be null", profile);
	}	

	@Test
	public void testInvalidUri()
	{
		String uri = "http://www.google.com/";
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.resolveSteamProfile(uri);
		
		assertNull("Profile should be null", profile);
	}	

	/*
	@Test
	public void testNonXmlUri()
	{
		String uri = "http://www.steamcommunity.com/id/raveturned";
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.resolveSteamProfile(uri);
		
		assertNull("Profile should be null", profile);
	}
	*/
	
	@Test
	public void testPoorConfig()
	{
		String profileuri = "http://www.steamcommunity.com/id/raveturned?xml=1";
		String gamesuri = "http://www.steamcommunity.com/id/raveturned/games?xml=1";

		   DocumentBuilderFactory factory = new DocumentBuilderFactory()
		   {
		      public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException
		      {
		         throw new ParserConfigurationException("Testing ParserConfigurationException");
		      }

		      public void setAttribute(String name, Object value) throws IllegalArgumentException
		      {

		      }

		      public Object getAttribute(String name) throws IllegalArgumentException
		      {
		         return null;
		      }

			@Override
			public boolean getFeature(String arg0) throws ParserConfigurationException {
				return false;
			}

			@Override
			public void setFeature(String arg0, boolean arg1) throws ParserConfigurationException {
				
			}
		   };
		
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.resolveSteamProfile(profileuri, factory);
		SteamGame[] gameslist = repo.resolveSteamGames(gamesuri, factory);
		
		assertNull("Profile should be null", profile);
		assertEquals("Games list should be empty", 0, gameslist.length);

	}
	
	@Test
	public void getProfileById()
	{
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.getSteamProfile(76561197970485997l);
		
		assertNotNull("Profile should not be null", profile);
		assertEquals("Id should be value specified at creation", 76561197970485997l, profile.getId());
		assertTrue("Name should be value specified at creation", "raveturned".equalsIgnoreCase(profile.getName()));

	}

	@Test
	public void getProfileByName()
	{
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.getSteamProfile("raveturned");
		
		assertNotNull("Profile should not be null", profile);
		assertEquals("Id should be value specified at creation", 76561197970485997l, profile.getId());
		assertTrue("Name should be value specified at creation", "raveturned".equalsIgnoreCase(profile.getName()));

	}
	
	@Test
	public void getGamesById()
	{
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamGame[] games = repo.getSteamGamesById(76561198030489958l);
		
		assertNotNull("Games list should not be null", games);
		assertTrue("Should be more than 5 games", games.length > 5);
		System.err.println("Found games: "+games.length);
	}

	@Test
	public void getGamesByName()
	{
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamGame[] games = repo.getSteamGamesByName("freakyflyingmonkey");
		
		assertNotNull("Games list should not be null", games);
		assertTrue("Should be more than 5 games", games.length > 5);
		System.err.println("Found games: "+games.length);
	}	

	@Test
	public void getSmallGroupMembersList()
	{
		SteamXmlRepository repo = new SteamXmlRepository();
		long[] ids = repo.getSteamGroupMembers("flopsoc");
		
		assertNotNull("Member list should not be null", ids);
		assertTrue("Should be more than 5 members", ids.length > 5);
		System.err.println("Found members: "+ids.length);
	}	
	@Test
	public void getLargeGroupMembersList()
	{
		SteamXmlRepository repo = new SteamXmlRepository();
		long[] ids = repo.getSteamGroupMembers("L4D2boycott");
		
		assertNotNull("Member list should not be null", ids);
		assertTrue("Should be more than 1000 members", ids.length > 1000);
		System.err.println("Found members: "+ids.length);
	}	

}
