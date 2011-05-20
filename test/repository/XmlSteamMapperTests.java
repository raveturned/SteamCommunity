package repository;

import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import model.*;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlSteamMapperTests extends TestCase {

	//profile
	private String validProfileXml="<profile><steamID64>-1</steamID64><steamID>test</steamID></profile>";
	private String invalidProfileXml="<profile><steamID64>trash</steamID64><steamID>test</steamID></profile>";
	//game
	private String validGameXml="<game><appID>-1</appID><name>test</name></game>";
	private String invalidGameXml="<game><appID>trash</appID><name>test</name></game>";
	//gameslist
	private String validGamesListXml="<gameslist><game><appID>-1</appID><name>test</name></game></gameslist>";
	private String invalidGamesListXml="<gameslist><game><appID>trash</appID><name>test</name></game></gameslist>";
	//group
	private String validGroupXml="<group><groupID64>-1</groupID64><groupName>test</groupName></group>";
	private String invalidGroupXml="<group><groupID64>trash</groupID64><groupName>test</groupName></group>";
	//grouplist
	private String validGroupListXml="<groups><group><groupID64>-1</groupID64><groupName>test</groupName></group></groups>";
	private String invalidGroupListXml="<groups><group><groupID64>trash</groupID64><groupName>test</groupName></group></groups>";

	
	
	private DocumentBuilder db;
	private XmlSteamMapper mapper;

	
    protected void setUp() {
    	try {
    		mapper = new XmlSteamMapper();
			db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
		
	@Test
	public void testValidProfileXml()
	{
		InputStream is = new ByteArrayInputStream( validProfileXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		SteamProfile profile = mapper.mapXmlToProfile(ele);
		
		assertEquals("Correct id", -1, profile.getId());
		assertEquals("Correct name", "test", profile.getName());
		
	}

	@Test
	public void testInvalidProfileXml()
	{
		InputStream is = new ByteArrayInputStream( invalidProfileXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		SteamProfile profile = mapper.mapXmlToProfile(ele);
		
		assertNull("Profile should be null", profile);
	}

	@Test
	public void testValidGameXml()
	{
		InputStream is = new ByteArrayInputStream( validGameXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		SteamGame game = mapper.mapXmlToGame(ele);
		
		assertEquals("Correct id", -1, game.getId());
		assertEquals("Correct name", "test", game.getName());
	}	
	
	@Test
	public void testInvalidGameXml()
	{
		InputStream is = new ByteArrayInputStream( invalidGameXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		SteamGame game = mapper.mapXmlToGame(ele);
		
		assertNull("Game should be null", game);
	}	
	

	@Test
	public void testValidGamesListXml()
	{
		InputStream is = new ByteArrayInputStream( validGamesListXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		ArrayList<SteamGame> gameslist = mapper.mapXmlToGamesList(ele);
		
		assertNotNull("Games list should not be null", gameslist);
		assertEquals("List should have one element", 1, gameslist.size());
		
		SteamGame game = gameslist.get(0);
		
		assertEquals("Correct id", -1, game.getId());
		assertEquals("Correct name", "test", game.getName());


	}	
	
	@Test
	public void testInvalidGamesListXml()
	{
		InputStream is = new ByteArrayInputStream( invalidGamesListXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		ArrayList<SteamGame> gameslist = mapper.mapXmlToGamesList(ele);
		
		assertNotNull("Games list should not be null", gameslist);
		assertEquals("List should be empty", 0, gameslist.size());
	}	
	

	@Test
	public void testValidGroupXml()
	{
		InputStream is = new ByteArrayInputStream( validGroupXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		SteamGroup group = mapper.mapXmlToGroup(ele);
		
		assertEquals("Correct id", -1, group.getId());
		assertEquals("Correct name", "test", group.getName());
	}	
	
	@Test
	public void testInvalidGroupXml()
	{
		InputStream is = new ByteArrayInputStream( invalidGroupXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		SteamGroup group = mapper.mapXmlToGroup(ele);
		
		assertNull("Group should be null", group);
	}	
	

	@Test
	public void testValidGroupListXml()
	{
		InputStream is = new ByteArrayInputStream( validGroupListXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		ArrayList<SteamGroup> grouplist = mapper.mapXmlToGroupList(ele);
		
		assertNotNull("Group list should not be null", grouplist);
		assertEquals("List should have one elemeent", 1, grouplist.size());
		
		SteamGroup group = grouplist.get(0);
		
		assertEquals("Correct id", -1, group.getId());
		assertEquals("Correct name", "test", group.getName());


	}	
	
	@Test
	public void testInvalidGroupListXml()
	{
		InputStream is = new ByteArrayInputStream( invalidGroupListXml.getBytes() );
		Document result = null;

		assertNotNull("DocBuilder should not be null", db);
		assertNotNull("InputStream should not be null", is);

		try {
			result = db.parse(is);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull("Result should not be null", result);
		Element ele = result.getDocumentElement();
		assertNotNull("Document element should not be null", ele);
		ArrayList<SteamGroup> grouplist = mapper.mapXmlToGroupList(ele);
		
		assertNotNull("Group list should not be null", grouplist);
		assertEquals("List should be empty", 0, grouplist.size());
	}	

}