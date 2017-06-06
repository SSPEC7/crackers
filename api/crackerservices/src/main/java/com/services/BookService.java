package com.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.models.Book;

/**
 *  @author RITESH SINGH 
 */
public interface BookService {

	public Book save(Book book);
	public Long count();
	public List<Book> getBooks();
	public List<Book> getBooks(Sort sort);
	public Book getBookById(String bookId);
}
