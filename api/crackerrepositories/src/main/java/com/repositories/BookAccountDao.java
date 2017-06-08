package com.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.models.BookAccount;

/**
 * @author RITESH SINGH
 */
public interface BookAccountDao extends PagingAndSortingRepository<BookAccount, String> {
	
	public List<BookAccount> getBookAccountBybookId(String bookId);
	public List<BookAccount> getBookAccountByisActive(Boolean isActive);
	//public List<BookAccount> getBookAccountBybookIdAndIsActiveIsFalse(String bookId, Boolean isActive);
}
