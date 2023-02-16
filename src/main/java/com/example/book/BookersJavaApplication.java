package com.example.book;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;

@SpringBootApplication
public class BookersJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookersJavaApplication.class, args).getBean(BookersJavaApplication.class).execute();
	}
	
	@Autowired
	BookRepository repository;
	
	private void execute() {
//		setup();
//		showList();
//		showOne();
//		updateBook();
		deleteBook();
	}
	
	private void setup() {
		Book book1 = new Book(null, "リーダブルコード", "より良いコードを書くためのシンプルで実践的なテクニック");
		book1 = repository.save(book1);
		System.out.println("登録したデータは、" + book1 + "です。");
		
		Book book2 = new Book(null, "トラブル知らずのシステム設計", "システム設計の要点を図解で説明してくれます");
		book1 = repository.save(book2);
		System.out.println("登録したデータは、" + book2 + "です。");
	}
	
	private void showList() {
		System.out.println("---全件取得開始---");
		
		Iterable<Book> books = repository.findAll();
		for(Book book : books) {
			System.out.println(book);
		}
		System.out.println("---全件取得完了---");
	}
	
	private void showOne() {
		System.out.println("---1件取得開始---");
		
		Optional<Book> bookOpt = repository.findById(1);
		if(bookOpt.isPresent()) {
			System.out.println(bookOpt.get());
		} else {
			System.out.println("該当する問題が存在しません・・・");
		}
		System.out.println("---1件取得完了---");
	}
	
	private void updateBook() {
		System.out.println("---更新処理開始---");
		
		Book book1 = new Book(1, "たのしいRuby", "Rubyの入門におすすめです");
		
		book1 = repository.save(book1);
		
		System.out.println("更新したデータは、" + book1 + "です。");
		System.out.println("---更新処理終了---");
	}
	
	private void deleteBook() {
		System.out.println("---削除処理開始---");
		repository.deleteById(2);
		System.out.println("---削除処理終了---");
	}

}
