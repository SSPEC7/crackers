package com.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.models.BookTransactionHistory;

/**
 * @author RITESH SINGH
 */
public interface BookTransactionHistoryDao extends PagingAndSortingRepository<BookTransactionHistory, String> {

}
