package com.services;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.models.BookTransactionHistory;

/**
 *  @author RITESH SINGH 
 */
public interface BookTransactionHistoryService {

	public BookTransactionHistory save(BookTransactionHistory bookTransactionHistory);
	public Long count();
	public List<BookTransactionHistory> getBookTransactionHistories();
	public List<BookTransactionHistory> getBookTransactionHistories(Sort sort);
	public BookTransactionHistory getBookTransactionHistoryById(Long bookTransactionHistoryId);
}
