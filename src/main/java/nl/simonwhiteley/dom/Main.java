package nl.simonwhiteley.dom;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) {
		DOMParser parser = new DOMParser();
		ArrayList<Book> books = parser.getBookList();
		ArrayList<Category> categories = parser.getCategoryList();
		
		Bookshelf mijnBookshelf = new Bookshelf("Mijn Bookshelf");
		mijnBookshelf.setBooks(books);
		mijnBookshelf.setCategories(categories);
	/*	
		Category cat1 = new Category("Roman");
		Category cat2 = new Category("Thriller");
		mijnBookshelf.addCategory(cat1);
		mijnBookshelf.addCategory(cat2);
		mijnBookshelf.addBook(new Book("Boek 1", cat1));
		mijnBookshelf.addBook(new Book("Boek 2", cat1));
		mijnBookshelf.addBook(new Book("Nightmare on Sesamestreet", cat2));
		*/
		System.out.println("Bookshelf heeft " + mijnBookshelf.getBookCount() + " boeken");
		
		for (Category category : mijnBookshelf.getCategories()) {
			ArrayList<Book> boeken = mijnBookshelf.getBooksByCategory(category);
			System.out.println("Category " + category.getName());
			for(Book book : boeken) {
				System.out.println("\t" + book.getName());
			}
		}
		System.out.println("Met succes afgerond.");
	}

}
