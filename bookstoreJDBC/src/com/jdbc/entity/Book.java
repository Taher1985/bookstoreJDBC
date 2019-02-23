package com.jdbc.entity;

import java.util.List;

public class Book {

	private String isbn;
	private String name;
	private Publisher publisher;
	private List<Chapter> chapters;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	@Override
	public String toString() {
		return "Book Details are \nISBN : " + isbn + " and Book Name : " + name + " \n\nPublisher Details are "
				+ publisher + " \n\nChapters Details are : \n" + chapters;
	}

}
