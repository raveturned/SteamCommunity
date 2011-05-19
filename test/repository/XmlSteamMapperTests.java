package repository;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import model.SteamProfile;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import static org.junit.Assert.*;


public class XmlSteamMapperTests extends TestCase {

	private String validProfileXml="<profile><steamID64>-1</steamID64><steamID>test</steamID></profile>";
	private DocumentBuilder db;

	
    protected void setUp() {
    	try {
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
		SteamProfile profile = XmlSteamMapper.mapXmlToProfile(ele);
		
		assertEquals("Correct id", -1, profile.getId());
		assertEquals("Correct name", "test", profile.getName());
		
	}

	@Test @Ignore
	public void testInvalidProfileXml()
	{
		fail("Not implemented");		
	}

	@Test @Ignore
	public void testValidGameXml()
	{
		fail("Not implemented");
	}	
	
	@Test @Ignore
	public void testInvalidGameXml()
	{
		fail("Not implemented");
	}	
	

	@Test @Ignore
	public void testValidGamesListXml()
	{
		fail("Not implemented");
	}	
	
	@Test @Ignore
	public void testInvalidGamesListXml()
	{
		fail("Not implemented");
	}	
	
}
