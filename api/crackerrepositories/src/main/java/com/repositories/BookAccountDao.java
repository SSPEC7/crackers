package com.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.models.BookAccount;

/**
 * @author RITESH SINGH
 */
public interface BookAccountDao extends PagingAndSortingRepository<BookAccount, String> {
	
}
