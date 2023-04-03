package com.example.book.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping("/books")
	public String index(BookForm bookForm, Model model) {
		
		Iterable<Book> list = service.selectAll();
		
		model.addAttribute("list", list);
		return "index";
	}
	
	@PostMapping("/create")
	public String insert(@Validated BookForm bookForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		Book book = new Book();
		book.setTitle(bookForm.getTitle());
		book.setBody(bookForm.getBody());
		
		if(!bindingResult.hasErrors()) {
			service.insertBook(book);
			redirectAttributes.addFlashAttribute("complete", "Book was successfully created.");
			return "redirect:/book/" + book.getId();
		} else {
			return index(bookForm, model);
		}
	}
	
	@GetMapping("/book/{id}")
	public String show(@PathVariable Integer id, Model model) {
		Optional<Book> bookOpt = service.selectOneById(id);
		model.addAttribute("bookOpt", bookOpt.get());
		return "show";
	}
	
	@GetMapping("/book/{id}/edit")
	public String update(BookForm bookForm, @PathVariable Integer id, Model model) {
		//Bookを取得(Optionalでラップ)
		Optional<Book> bookOpt = service.selectOneById(id);
		//BookFormへ詰め直し
		Optional<BookForm> bookFormOpt = bookOpt.map(t -> makeBookForm(t));
		//BookFormがnullでなければ中身を取り出す
		if(bookFormOpt.isPresent()) {
			bookForm = bookFormOpt.get();
		}
		//更新用のModelを作成する
		makeUpdateModel(bookForm, model);
		return "edit";
	}
	
	//更新用のModelを作成する
	private void makeUpdateModel(BookForm bookForm, Model model) {
		model.addAttribute("id", bookForm.getId());
		model.addAttribute("bookForm", bookForm);
	}
	
	//idをkeyにしてデータを更新する
	@PostMapping("/update")
	public String update(@Validated BookForm bookForm, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		//BookFormからBookに詰めなおす
		Book book = makeBook(bookForm);
		//入力チェック
		if(!result.hasErrors()) {
			//更新処理、フラッシュスコープの使用、リダイレクト(個々の編集ページ)
			service.updateBook(book);
			redirectAttributes.addFlashAttribute("complete", "Book was successfully updated.");
			//更新画面を表示する
			return "redirect:/book/" + book.getId();
		} else {
			//更新用のModelを作成する
			makeUpdateModel(bookForm, model);
			return "edit";
		}
	}
	
	//BookFormからBookに詰めなおして戻り値として返す
	private Book makeBook(BookForm bookForm) {
		Book book = new Book();
		book.setId(bookForm.getId());
		book.setTitle(bookForm.getTitle());
		book.setBody(bookForm.getBody());
		return book;
	}
	
	//BookからBookFormに詰めなおして戻り値として返す
	private BookForm makeBookForm(Book book) {
		BookForm form = new BookForm();
		form.setId(book.getId());
		form.setTitle(book.getTitle());
		form.setBody(book.getBody());
		return form;
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("id") String id, Model model, RedirectAttributes redirectAttributes) {
		service.deleteBookById(Integer.parseInt(id));
		redirectAttributes.addFlashAttribute("delcomplete", "Book was successfully destroyed.");
		return "redirect:/books";
	}
}
