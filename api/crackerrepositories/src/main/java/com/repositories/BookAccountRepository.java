package com.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.models.BookAccount;

/**
 * @author RITESH SINGH
 *
 */
public interface BookAccountRepository extends MongoRepository<BookAccount, String> {

	public List<BookAccount> getBookAccountsByBookId(String bookId);
	public List<BookAccount> getBookAccountsByStatus(Boolean status);
	public List<BookAccount> getBookAccountsByBookIdAndStatus(String bookId,Boolean status);
}
