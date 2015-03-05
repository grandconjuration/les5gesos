package nl.simonwhiteley;

import java.util.ArrayList;

public class Book {
	
	private String name;
	private ArrayList<Category> cats = new ArrayList<Category>();
	
	public Book(String nm) {
		this.setName(nm);
	}
	
	public Book(String nm, Category cat) {
		this.setName(nm);
		this.addCategory(cat);
	}
	
	public void addCategory(Category cat) {
		cats.add(cat);
	}
	
	public boolean removeCategory(Category cat) {
		return cats.remove(cat);
	}

	public ArrayList<Category> getCats() {
		return cats;
	}

	public void setCats(ArrayList<Category> cats) {
		this.cats = cats;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}

