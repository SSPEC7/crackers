package com.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.models.Book;

/**
 *  @author RITESH SINGH 
 */
public interface BookService {

	public Book save(Book book);
	public Book update(Book book);
	public Long count();
	public List<Book> getBooks();
	public List<Book> getBooks(Sort sort);
	public Page<Book> getBooks(Pageable pageable);
	public Book getBookById(String bookId);
	
}
