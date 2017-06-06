package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.models.BookTransactionHistory;

/**
 * @author RITESH SINGH
 *
 */
public interface BookTransactionHistoryRepository extends MongoRepository<BookTransactionHistory, String> {

}
