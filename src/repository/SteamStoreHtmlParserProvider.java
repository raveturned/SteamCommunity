package repository;

import java.util.ArrayList;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.HTMLEditorKit;

class SteamStoreCallback extends HTMLEditorKit.ParserCallback {

	ArrayList<String> names = new ArrayList<String>();
	boolean insideSpecs = false;
	boolean insideName = false;
	int depth=0;
	String name = null;
		
	  public void handleStartTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
		  if(insideSpecs)
		  {
			  depth++;
			  //System.err.println("open:"+tag);
			  
			  if (tag.equals(Tag.DIV) &&
				  attributes.containsAttribute(HTML.Attribute.CLASS, "name"))
			  {
				  name = "";
				  insideName=true;
			  }
		  }
		  
		  if (tag.equals(Tag.DIV))
		  {
			  if (attributes.containsAttribute(HTML.Attribute.CLASS, "game_area_details_specs"))
			  {
				  insideSpecs = true;
				  //System.err.println("Found spec at position "+ position);
			  }
		  }
		  //System.err.println("tag: "+tag.DIV);		  
	  }
	  
	  @Override
	  public void handleSimpleTag(HTML.Tag tag, MutableAttributeSet attributes, int position) {
		  if (insideSpecs)
		  {
			  //System.err.println("simple:"+tag);
		  }
	  }
	  
	  @Override
	  public void handleEndTag(HTML.Tag tag, int position)
	  {
		  //this.listAttributes(attributes);
		  if (insideSpecs)
		  {
			  
			  
			  if(depth==0)
			  {
				  insideSpecs=false;
				  //System.err.println("---");
			  }
			  else
			  {
				  //System.err.println("close:"+tag);
				  depth--;
				  if (insideName)
				  {
					  endName();
				  }
			  }
		  }
	  }
	  
	  private void endName() {
		  insideName = false;
		  names.add(name);
	  }

	@Override
	  public void handleText(char[] data, int pos)
	  {
		  if (insideName)
		  {
			  name += new String(data);
		  }
	  }
	
	public String[] getResults()
	{
		return names.toArray(new String[names.size()]);
	}

	}

	class SteamStoreHtmlParserProvider extends HTMLEditorKit {
	  /**
		 * 
		 */
		private static final long serialVersionUID = 1L;

	public HTMLEditorKit.Parser getParser() {
	    return super.getParser();
	  }
	}