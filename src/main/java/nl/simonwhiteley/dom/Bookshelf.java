package nl.simonwhiteley.dom;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="bookshelf", namespace="http://www.hu.nl/schemas/books")
public class Bookshelf extends Category {

	private ArrayList<Book> books = new ArrayList<Book>();
	private ArrayList<Category> categories = new ArrayList<Category>();
	
	public Bookshelf() {}

	@XmlElementWrapper(name="categories", namespace="http://www.hu.nl/schemas/books")	
	@XmlElement(name="category", namespace="http://www.hu.nl/schemas/books")
	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	public void addCategory(Category c) {
		this.categories.add(c);
	}

	public boolean removeCategory(Category c) {
		return this.categories.remove(c);
	}

	public Bookshelf(String n) {
		super(null, n);
	}

	@XmlElementWrapper(name="books", namespace="http://www.hu.nl/schemas/books")	
	@XmlElement(name="book", namespace="http://www.hu.nl/schemas/books")
	public ArrayList<Book> getBooks() {
		return books;
	}

	public int getBookCount() {
		return this.books.size();
	}

	public ArrayList<Book> getBooksByCategory(Category cat) {
		ArrayList<Book> foundBooks = new ArrayList<Book>();
		for (Book book : books) {
			ArrayList<Category> cats = book.getCats();
			for (Category cate : cats) {

				if (cate.equals(cat)) {
					foundBooks.add(book);
				}
			}
		}
		return foundBooks;
	}

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public void addBook(Book b) {
		books.add(b);
	}

	public boolean removeBook(Book b) {
		return books.remove(b);
	}

}
