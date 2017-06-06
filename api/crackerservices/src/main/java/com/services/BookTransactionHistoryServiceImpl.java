package com.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.models.BookTransactionHistory;
import com.repositories.BookTransactionHistoryRepository;

/**
 * @author RITESH SINGH
 */
@Service("bookTransactionHistoryService")
@ComponentScan("com.repositories")
public class BookTransactionHistoryServiceImpl implements BookTransactionHistoryService {

	@Autowired
	@Qualifier("bookTransactionHistoryRepository")
	private BookTransactionHistoryRepository bookTransactionHistoryRepository;

	@Override
	public BookTransactionHistory save(BookTransactionHistory bookTransactionHistory) {
		if(bookTransactionHistory != null){
			return bookTransactionHistoryRepository.save(bookTransactionHistory);
		}else
			return null;
	}

	@Override
	public Long count() {
		return bookTransactionHistoryRepository.count();
	}

	@Override
	public List<BookTransactionHistory> getBookTransactionHistories() {
		return bookTransactionHistoryRepository.findAll();
	}

	@Override
	public List<BookTransactionHistory> getBookTransactionHistories(Sort sort) {
		return bookTransactionHistoryRepository.findAll(sort);
	}

	@Override
	public BookTransactionHistory getBookTransactionHistoryById(Long bookTransactionHistoryId) {
		return bookTransactionHistoryRepository.findOne(bookTransactionHistoryId);
	}
}
