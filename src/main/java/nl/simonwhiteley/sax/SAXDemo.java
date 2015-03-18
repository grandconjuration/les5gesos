package nl.simonwhiteley.sax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


import org.xml.sax.SAXException;

public class SAXDemo {
	private static final SAXParserFactory SAX = SAXParserFactory.newInstance();

	private static final Logger log = Logger.getLogger(SAXDemo.class.getName());

	public static void main(String[] args) {
		try {
			SAXParser saxParser = SAX.newSAXParser();
			Path xmlLocation = Paths.get("src/bookshelf.xml");
			if (Files.isRegularFile(xmlLocation)) {
				File file = new File(xmlLocation.toUri());
				saxParser.parse(file, new EchoContentHandler());
			}else {
				throw new FileNotFoundException("Bestand voor "+ xmlLocation.normalize().toAbsolutePath() +" bestaat niet");
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			log.log(Level.SEVERE,
					"Kan niet verder met verwerken door problemen", e);
		}
	}
}
