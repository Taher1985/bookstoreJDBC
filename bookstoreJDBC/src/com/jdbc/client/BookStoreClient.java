package com.jdbc.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.entity.Book;
import com.jdbc.entity.Chapter;
import com.jdbc.entity.Publisher;
import com.jdbc.service.BookStoreService;

public class BookStoreClient {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Publisher publisher1 = new Publisher();
		publisher1.setCode("ABC");
		publisher1.setName("John");
		
		Publisher publisher2 = new Publisher();
		publisher2.setCode("DEF");
		publisher2.setName("Michael");

		Chapter chapter1 = new Chapter("OOPs", 1);
		Chapter chapter2 = new Chapter("Collections", 2);
		List<Chapter> chapters1 = new ArrayList<>();
		chapters1.add(chapter1);
		chapters1.add(chapter2);

		Chapter chapter3 = new Chapter("Threads", 3);
		Chapter chapter4 = new Chapter("Strings", 4);
		List<Chapter> chapters2 = new ArrayList<>();
		chapters2.add(chapter3);
		chapters2.add(chapter4);
		
		Book book1 = new Book();
		book1.setIsbn("123ABC");
		book1.setName("CoreJava");
		book1.setPublisher(publisher1);
		book1.setChapters(chapters1);
		
		Book book2 = new Book();
		book2.setIsbn("456DEF");
		book2.setName("ThreadJava");
		book2.setPublisher(publisher2);
		book2.setChapters(chapters2);

		BookStoreService bookStoreService = new BookStoreService();
		/*bookStoreService.persistBookStore(book1);
		bookStoreService.persistBookStore(book2);*/
		
		Book book = bookStoreService.retreieveBookStore("456DEF");
		System.out.println(book);
	}

}
