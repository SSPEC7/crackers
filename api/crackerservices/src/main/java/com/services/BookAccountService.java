package com.services;

import java.util.List;

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
	public BookAccount getBookAccountById(String bookAccountId);
}
