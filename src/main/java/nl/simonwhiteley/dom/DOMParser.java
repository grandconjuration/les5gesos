package nl.simonwhiteley.dom;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.dom.ChildNode;

public class DOMParser {
	static ArrayList<String> categories = new ArrayList<String>();
	static ArrayList<String> books = new ArrayList<String>();

	static HashMap<Integer, Category> categoryList = new HashMap<Integer, Category>();
	static ArrayList<Book> bookList = new ArrayList<Book>();
	
	static ArrayList<Category> categoryArrayL = new ArrayList<Category>();

	public DOMParser() {
		Path xmlLocation = Paths.get("src/bookshelf.xml");
		System.out.println(xmlLocation.toAbsolutePath());
		if (Files.isRegularFile(xmlLocation)) {
			try (InputStream in = Files.newInputStream(xmlLocation);
					Reader reader = new InputStreamReader(in, "UTF-8")) {
				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
						.newInstance();
				docBuilderFactory.setNamespaceAware(true);
				DocumentBuilder docBuilder = docBuilderFactory
						.newDocumentBuilder();
				InputSource is = new InputSource(reader);
				is.setEncoding("UTF-8");
				Document document = docBuilder.parse(in);
				document.getDocumentElement().normalize();
				System.out.println("Document ingelezen root element naam is "
						+ document.getDocumentElement().getNodeName());

				System.out.println("Aantal child elementen: "
						+ document.getDocumentElement().getChildNodes()
								.getLength());

				for (int i = 0; i < document.getDocumentElement()
						.getChildNodes().getLength(); i++) {
					Node curNode = document.getDocumentElement()
							.getChildNodes().item(i);
					if (curNode.getNodeType() == Node.ELEMENT_NODE) {
						System.out.println("child: " + curNode.getLocalName());
						if (curNode.getLocalName() == "categories") {
							NodeList children = curNode.getChildNodes();

							doSomethingWithCategories(children);

							/*
							 * for (int j = 0; j < children.getLength(); j++) {
							 * Node childNode = children.item(j); String
							 * newCategory = childNode.getTextContent()
							 * .replace(String.valueOf((char) 160), " ")
							 * .trim(); CategoryList.add(newCategory); }
							 */
						} else if (curNode.getLocalName() == "books") {
							NodeList children = curNode.getChildNodes();
							// System.out.println("curnode " +
							// curNode.getLocalName());
							doSomethingWithBooks(children);
						}
					}
				}

			/*	System.out.println("Categories:");
				for (String name : categories) {
					System.out.println(" test  : " + name);
				}
				System.out.println("Books:");
				for (String title : books) {
					System.out.println(title);
				}*/
			} catch (IOException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("oeps");
		}
	}

	static void doSomethingWithCategories(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (childNode.getNodeName().equals("category")) {
					Element eElement = (Element) childNode;
					categories.add(eElement.getElementsByTagName("name")
							.item(0).getTextContent());
					Category IamRunningOutOfVariableNames = new Category(eElement.getElementsByTagName("name")
							.item(0).getTextContent());
					categoryList.put(
							Integer.parseInt(eElement.getAttribute("id")),
							IamRunningOutOfVariableNames);
					categoryArrayL.add(IamRunningOutOfVariableNames);
					
				}

				NodeList children = childNode.getChildNodes();
				if (children != null) {
					doSomethingWithCategories(children);
				}
			}
		}
	}
	
	public ArrayList<Book> getBookList() {
		return bookList;
	}
	
	public ArrayList<Category> getCategoryList() {
		return categoryArrayL;
	}

	static void doSomethingWithBooks(NodeList nodeList) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node childNode = nodeList.item(i);
			if (childNode.getNodeType() == Node.ELEMENT_NODE) {
				//Element eElement = (Element) childNode;
				//books.add(eElement.getElementsByTagName("title").item(0)
					//	.getTextContent().trim());
				if (childNode.getNodeName().equals("book")) {
					Element anotherElement = (Element) childNode;
					books.add(anotherElement.getElementsByTagName("title")
							.item(0).getTextContent());
					Book myBook = new Book(anotherElement
							.getElementsByTagName("title").item(0)
							.getTextContent().trim());
					NodeList bookCatList = childNode.getChildNodes();

					for (int j = 0; j < bookCatList.getLength(); j++) {
						Node childNode2 = bookCatList.item(j);
						// System.out.println("contentstuff:"
						// +childNode2.getTextContent());
						// System.out.println("right here: "
						// +childNode2.getNodeName());
						if (childNode2.getNodeName().equals("categories")) {

							Element eElement2 = (Element) childNode2;
							

							NodeList categories = childNode2.getChildNodes();
							for (int z = 0; z < categories.getLength(); z++) {
								Node thisCat = categories.item(z);

								if (thisCat.getNodeType() == Node.ELEMENT_NODE) {

									Element eElement3 = (Element) thisCat;

									int catId = Integer.parseInt(eElement3.getAttribute("refid"));
									Category myCat = categoryList.get(catId);
									myBook.addCategory(myCat);

								}
							}
						}
					}
					bookList.add(myBook);
				}
			}
		}
	}
}
