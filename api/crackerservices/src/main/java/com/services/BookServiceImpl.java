package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.exception.BookException;
import com.modelUtility.EditableInfo;
import com.models.Book;
import com.repositories.BookRepository;

/**
 * @author RITESH SINGH
 */
@Service("bookService")
@ComponentScan("com.repositories")
public class BookServiceImpl implements BookService {

	@Autowired
	@Qualifier("bookRepository")
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		
		validate(book);
		return bookRepository.save(book);
	}

	private void validate(Book book){
		try{
			if(!StringUtils.isEmpty(book)){
				if(book.getBookName()==null){
					String message = String.format("Book-Name can not be blank.");
					throw new BookException(message);
				}
				if(book.getBookCode()==null){
					String message = String.format("Book-Code can not be blank.");
					throw new BookException(message);
				}
				
				Book.Account account = (new Book()).new Account();
				account.setIsDiscount(false);
				account.setMrp(00000D);
				account.setQuantity(00L);
				account.setUnit("XXXX");
				book.setAccount(account);
				book.setIsAvailable(false);
				book.setIsOffer(false);
				EditableInfo editableInfo = new EditableInfo();
				editableInfo.setCreatedAt();
				editableInfo.setCreatedBy("Super-Admin");
				editableInfo.setUpdatedAt();
				editableInfo.setUpdatedBy("Super-Admin");
				book.setEditableInfo(editableInfo);
				
			}
		}catch (Exception e) {
			String message = String.format("Error while validating Book for create.");
			throw new BookException(message, e);
		}
	}
	
	@Override
	public Long count() {
		return bookRepository.count();
	}

	@Override
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getBooks(Sort sort) {
		return bookRepository.findAll(sort);
	}

	@Override
	public Book getBookById(String bookId) {
		return bookRepository.findOne(bookId);
	}
}
