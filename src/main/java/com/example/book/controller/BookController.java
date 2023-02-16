package com.example.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.book.entity.Book;
import com.example.book.form.BookForm;
import com.example.book.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService service;
	
	@ModelAttribute
	public BookForm setUpForm() {
		BookForm form = new BookForm();
		return form;
	}
	
	@GetMapping("books")
	public String showList(BookForm bookForm, Model model) {
		
		Iterable<Book> list = service.selectAll();
		
		model.addAttribute("list", list);
		return "books";
	}
}
