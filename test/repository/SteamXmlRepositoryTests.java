package repository;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilderFactory;

import model.*;

import org.junit.Test;

public class SteamXmlRepositoryTests {


	
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

	@Test
	public void testNonxmlUri()
	{
		String uri = "http://www.steamcommunity.com/id/raveturned";
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.resolveSteamProfile(uri);
		
		assertNull("Profile should be null", profile);
	}
	
/*	@Test
	public void testPoorConfig()
	{
		String uri = "http://www.steamcommunity.com/id/raveturned?xml=1";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf..setValidating(false);
		dbf.setNamespaceAware(false);

		
		SteamXmlRepository repo = new SteamXmlRepository();
		SteamProfile profile = repo.resolveSteamProfile(uri, dbf);
		
		assertNull("Profile should be null", profile);

	}
	*/
}
