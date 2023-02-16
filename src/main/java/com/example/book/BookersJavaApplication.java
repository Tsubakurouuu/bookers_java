package com.example.book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.book.entity.Book;
import com.example.book.service.BookService;

@SpringBootApplication
public class BookersJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookersJavaApplication.class, args);
	}
	
	@Autowired
	BookService service;
	
	private void execute() {
//		setup();
//		showList();
//		showOne();
//		updateBook();
//		deleteBook();
	}
	
	private void setup() {
		System.out.println("---登録処理開始---");
		Book book1 = new Book(null, "リーダブルコード", "より良いコードを書くためのシンプルで実践的なテクニック");
		Book book2 = new Book(null, "トラブル知らずのシステム設計", "システム設計の要点を図解で説明してくれます");
		Book book3 = new Book(null, "入門Git", "gitについての基本操作から内部構造まで解説してくれます");
		Book book4 = new Book(null, "アルゴリズム図鑑", "アルゴリズムについてカラーイラストでしっかり解説してくれます");
		Book book5 = new Book(null, "達人プログラマー", "プログラマ入門者は必読！");
		
		List<Book> bookList = new ArrayList<>();
		
		Collections.addAll(bookList, book1, book2, book3, book4, book5);
		
		for(Book book : bookList) {
			service.insertBook(book);
		}
		
		System.out.println("---登録処理完了---");
	}
	
	private void showList() {
		System.out.println("---全件取得開始---");
		
		Iterable<Book> books = service.selectAll();
		for(Book book : books) {
			System.out.println(book);
		}
		System.out.println("---全件取得完了---");
	}
	
	private void showOne() {
		System.out.println("---1件取得開始---");
		
		Optional<Book> bookOpt = service.selectOneById(1);
		if(bookOpt.isPresent()) {
			System.out.println(bookOpt.get());
		} else {
			System.out.println("該当する本が存在しません・・・");
		}
		System.out.println("---1件取得完了---");
	}
	
	private void updateBook() {
		System.out.println("---更新処理開始---");
		
		Book book1 = new Book(1, "たのしいRuby", "Rubyの入門におすすめです");
		
		service.updateBook(book1);
		
		System.out.println("---更新処理終了---");
	}
	
	private void deleteBook() {
		System.out.println("---削除処理開始---");
		service.deleteBookById(2);
		System.out.println("---削除処理終了---");
	}

}
