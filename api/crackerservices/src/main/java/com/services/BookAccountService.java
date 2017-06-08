package com.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.models.BookAccount;

/**
 * @author RITESH SINGH
 */
public interface BookAccountService {
	public BookAccount save(BookAccount bookAccount);
	public BookAccount inActive(String accountNo);
	public BookAccount active(String accountNo);
	public Long count();
	public List<BookAccount> getBookAccounts();
	public List<BookAccount> getBookAccounts(Sort sort);
	public Page<BookAccount> getBookAccounts(Pageable pageable);
	public BookAccount getBookAccountById(String bookAccountId);
	public List<BookAccount> getBookAccountByStatus(Boolean status);
	public List<BookAccount> getBookAccountByBookId(String bookId);
	public List<BookAccount> getBookAccountsByBookIdAndStatus(String bookId,Boolean status);
}
