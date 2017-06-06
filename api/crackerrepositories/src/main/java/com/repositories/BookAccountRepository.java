package com.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.models.BookAccount;

/**
 * @author RITESH SINGH
 *
 */
public interface BookAccountRepository extends MongoRepository<BookAccount, String> {

}
