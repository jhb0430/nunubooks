package com.jhb0430.nunubooks.aladdin;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.ParserAdapter;

public class AladdinOpenAPIHandler extends DefaultHandler {

	public List<Item> Items;
	private Item currentItem;
	private boolean inItemElement = false;
	private String tempValue;

	public AladdinOpenAPIHandler( ){
		Items = new ArrayList<Item>();
	
	}
	
	public void startElement(String namespace, String localName, String qName, Attributes atts) {
		if (localName.equals("item")) {
			currentItem = new Item();
			inItemElement = true;
		} else if (localName.equals("title")) {
			tempValue = "";
		} else if (localName.equals("link")) {
			tempValue = "";
		}
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException{
		tempValue = tempValue + new String(ch,start,length);
	}

	public void endElement(String namespaceURI, String localName,String qName) {
		if(inItemElement){
			if (localName.equals("item")) {
				Items.add(currentItem);
				currentItem = null;
				inItemElement = false;
			} else if (localName.equals("title")) {
				currentItem.Title = tempValue;
			} else if (localName.equals("link")) {
				currentItem.Link = tempValue;
			}
		}
	}

	public void parseXml(String xmlUrl) throws Exception {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser sp = spf.newSAXParser();
            ParserAdapter pa = new ParserAdapter(sp.getParser());
            pa.setContentHandler(this);
			pa.parse(xmlUrl);
	}
}

