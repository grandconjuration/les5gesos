package nl.simonwhiteley.sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

public class EchoContentHandler extends DefaultHandler2 {
	private int identation = 0;
	private boolean ignoreWhitespaces = true;

	private static final String HEADER = String.format("%-20s %-50s %-12s\n",
			"Node", "signature", "Waarde/Tekst");

	@Override
	public void startDocument() throws SAXException {
		System.out.println(HEADER);
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println(HEADER);
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (attributes.getLength() == 0) {
			String elementDescription = newFormatterQName("<" + qName + ">");
			System.out.format("%-20s %-50s %12s\n", "start element",
					elementDescription, "");
		} else {
			String elementDescription = newFormatterQName("<" + qName);
			System.out.format("%-20s %-50s %12s\n", "start element",
					elementDescription, "");
			identation += elementDescription.length();
			for (int i = 0; i < attributes.getLength(); i++) {
				String attributeNaam = attributes.getQName(i);
				String value = attributes.getValue(i);
				String attribDescription = newFormatterQName(attributeNaam
						+ "=");
				System.out.format("%-20s %-50s %-12s\n", "attribuut",
						attribDescription, "\"" + value + "\"");
			}
			identation -= elementDescription.length();
			System.out.format("%-20s %-50s %12s\n", "", newFormatterQName(">"),
					"");

		}
		identation += 3;
	}

	private String newFormatterQName(String qname) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.identation; i++) {
			builder.append(' ');
		}
		builder.append(qname);
		return builder.toString();
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String s = new String(ch, start, length);
		if (ignoreWhitespaces) {
			s = s.trim();
		}
		System.out.format("%-20s %-50s %-12s\n", "text", "", "\"" + s + "\"");
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		identation -= 3;
		String elementDescription = newFormatterQName("</" + qName + ">");
		System.out.format("%-20s %-50s %12s\n", "end element",
				elementDescription, "");
	}
}
