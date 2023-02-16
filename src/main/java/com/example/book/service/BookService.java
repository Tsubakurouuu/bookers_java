package com.example.book.service;

import java.util.Optional;

import com.example.book.entity.Book;

public interface BookService {
	
	Iterable<Book> selectAll();
	
	Optional<Book> selectOneById(Integer id);
	
	void insertBook(Book book);
	
	void updateBook(Book book);
	
	void deleteBookById(Integer id);

}
