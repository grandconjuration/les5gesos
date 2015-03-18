package nl.simonwhiteley.sax;

import java.util.logging.Logger;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

public class LoggingHandler extends DefaultHandler2 {
	private static final Logger log = Logger.getLogger(LoggingHandler.class
			.getName());

	@Override
	public void startDocument() throws SAXException {
		log.info("Document start");
	}

	@Override
	public void endDocument() throws SAXException {
		log.info("Document end");
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		log.info("Element start " + qName);
	}

	public void characters(char[] chars, int start, int length)
			throws SAXException {

		String value = new String(chars, start, length);
		log.info("Value found: " + value);
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		log.info("Element end " + qName);
	}

}
