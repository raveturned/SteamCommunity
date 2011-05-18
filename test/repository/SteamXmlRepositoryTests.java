package repository;

import static org.junit.Assert.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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
		String uri = "http://www.steamcommunity.com/id/raveturned?xml=1";

		   DocumentBuilderFactory factory = new DocumentBuilderFactory()
		   {
		      public DocumentBuilder newDocumentBuilder() throws ParserConfigurationException
		      {
		         throw new ParserConfigurationException("Testing ParserConfigurationException handling");
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
		SteamProfile profile = repo.resolveSteamProfile(uri, factory);
		
		assertNull("Profile should be null", profile);

	}
	
}
