package com.san;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.san.domain.Book;
import com.san.h2.domain.Author;
import com.san.h2.repo.AuthorRepository;
import com.san.repo.BookRepository;
import com.san.service.AuthorService;
import com.san.service.BookService;

public class Main {

	static BookService bookService;
	static AuthorService authorService;
	static BookRepository productRepository;
	static AuthorRepository authorRepository;

	public static void main(ApplicationContext appContext) {
		bookService = appContext.getBean(BookService.class);
		authorService = appContext.getBean(AuthorService.class);
		productRepository = appContext.getBean(BookRepository.class);
		System.out.println("Goint to insert 1000 books");
		bookService.insertThousandsBooks();

		System.out.println("Searching first book published in 2011 ");
		List<Book> books = productRepository.findByPublishYear(2011);
		if (books != null && books.size() > 0) {
			System.out.println("First book published in 2011 is : " + books.get(0));
		}

		authorRepository = appContext.getBean(AuthorRepository.class);
		System.out.println("Goint to insert 10 Authors");
		authorService.insert10Authors();

		System.out.println("Listing all authors ");
		List<Author> authors = authorRepository.findAll();
		for (Author author : authors) {
			System.out.println("Author ID : " + author.getId() + ", Author name : " + author.getName());
		}
		try {
			System.out.println("Sleeping for 5 minutes");
			Thread.sleep(5 * 60 * 1000);
		} catch (Exception e) {
		}
		System.out.println("Exiting program");

	}

}
