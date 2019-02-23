package com.jdbc.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.entity.Book;
import com.jdbc.entity.Chapter;
import com.jdbc.entity.Publisher;

public class BookStoreService {

	private Connection connection = null;

	public void persistBookStore(Book book) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl.168.1.6", "system",
					"9893AaBb");

			PreparedStatement pstm = connection
					.prepareStatement("INSERT INTO PUBLISHERS (CODE, PUBLISHER_NAME) VALUES (?, ?)");
			pstm.setString(1, book.getPublisher().getCode());
			pstm.setString(2, book.getPublisher().getName());
			pstm.executeUpdate();
			pstm.close();

			pstm = connection.prepareStatement("INSERT INTO BOOK (ISBN, BOOK_NAME, PUBLISHER_CODE) VALUES (?, ?, ?)");
			pstm.setString(1, book.getIsbn());
			pstm.setString(2, book.getName());
			pstm.setString(3, book.getPublisher().getCode());
			pstm.executeUpdate();
			pstm.close();

			pstm = connection.prepareStatement("INSERT INTO CHAPTER (BOOK_ISBN, CHAPTER_NUM, TITLE) VALUES (?, ?, ?)");
			for (Chapter chapter : book.getChapters()) {
				pstm.setString(1, book.getIsbn());
				pstm.setInt(2, chapter.getChapterNumber());
				pstm.setString(3, chapter.getTitle());
				pstm.executeUpdate();
			}
			pstm.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Book retreieveBookStore(String bookISBN) {
		Book book = new Book();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/orcl.168.1.6", "system",
					"9893AaBb");

			PreparedStatement pstm = connection.prepareStatement(
					"SELECT * FROM BOOK b1 left join PUBLISHERS p1 ON b1.PUBLISHER_CODE = p1.CODE where b1.ISBN  = ?");
			pstm.setString(1, bookISBN);
			ResultSet resultSet = pstm.executeQuery();
			Publisher publisher = new Publisher();
			Chapter chapter = null;
			while (resultSet.next()) {
				book.setIsbn(resultSet.getString("ISBN"));
				book.setName(resultSet.getString("BOOK_NAME"));
				publisher.setCode(resultSet.getString("PUBLISHER_CODE"));
				publisher.setName(resultSet.getString("PUBLISHER_NAME"));
				book.setPublisher(publisher);
			}

			List<Chapter> chapters = new ArrayList<Chapter>();
			pstm = connection.prepareStatement(
					"select * from BOOK b1 left join Chapter c1 on b1.ISBN = c1.BOOK_ISBN where b1.ISBN = ?");
			pstm.setString(1, bookISBN);
			resultSet = pstm.executeQuery();
			while (resultSet.next()) {
				chapter = new Chapter();
				chapter.setChapterNumber(resultSet.getInt("CHAPTER_NUM"));
				chapter.setTitle(resultSet.getString("TITLE"));
				chapters.add(chapter);
			}
			book.setChapters(chapters);
			resultSet.close();
			pstm.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return book;
	}

}
