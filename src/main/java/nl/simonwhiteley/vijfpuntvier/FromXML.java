package nl.simonwhiteley.vijfpuntvier;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import nl.simonwhiteley.dom.Bookshelf;
import nl.simonwhiteley.dom.Category;

public class FromXML {
	
    public static void main(String[] args) {
    	File file = new File("src/bookshelf.xml");
    //	System.out.println(file.getAbsolutePath());
    	
    	try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Bookshelf.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Bookshelf shelf = (Bookshelf) jaxbUnmarshaller.unmarshal(file);
			System.out.println(shelf.getCategories().size());
			for(Category cat : shelf.getCategories()) {
				System.out.println(cat.getName());
				System.out.println("test");
			}
		//	System.out.println(shelf);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println("done");
    }

}
