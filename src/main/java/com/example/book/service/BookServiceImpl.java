package com.example.book.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {
	
	@Autowired
	BookRepository repository;

	@Override
	public Iterable<Book> selectAll() {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findAll();
	}

	@Override
	public Optional<Book> selectOneById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		return repository.findById(id);
	}

	@Override
	public void insertBook(Book book) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(book);
	}

	@Override
	public void updateBook(Book book) {
		// TODO 自動生成されたメソッド・スタブ
		repository.save(book);
	}

	@Override
	public void deleteBookById(Integer id) {
		// TODO 自動生成されたメソッド・スタブ
		repository.deleteById(id);
	}

}
