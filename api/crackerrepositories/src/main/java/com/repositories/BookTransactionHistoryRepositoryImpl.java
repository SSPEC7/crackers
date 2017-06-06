package com.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.models.BookTransactionHistory;

/**
 * @author RITESH SINGH
 *
 */
@Repository("bookTransactionHistoryAccountRepository")
public class BookTransactionHistoryRepositoryImpl implements BookTransactionHistoryRepository {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;

	@Autowired
	private BookTransactionHistoryDao bookTransactionHistoryDao;

	@Override
	public <S extends BookTransactionHistory> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookTransactionHistory> findAll() {
		try{
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "_id"));
			List<BookTransactionHistory> bookTransactionHistories =  mongoTemplate.find(query, BookTransactionHistory.class);
			return bookTransactionHistories;
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public List<BookTransactionHistory> findAll(Sort sort) {
		
		try{
			Query query = new Query();
			query.with(sort);
			List<BookTransactionHistory> bookTransactionHistories =  mongoTemplate.find(query, BookTransactionHistory.class);
			return bookTransactionHistories;
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public <S extends BookTransactionHistory> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookTransactionHistory> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookTransactionHistory> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookTransactionHistory> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BookTransactionHistory> findAll(Pageable pageable) {
		
		return bookTransactionHistoryDao.findAll(pageable);
	}

	@Override
	public <S extends BookTransactionHistory> S save(S entity) {
		
		return bookTransactionHistoryDao.save(entity);
	}

	@Override
	public BookTransactionHistory findOne(String id) {
		
		return bookTransactionHistoryDao.findOne(id);
	}

	@Override
	public boolean exists(String id) {
		
		return bookTransactionHistoryDao.exists(id);
	}

	@Override
	public Iterable<BookTransactionHistory> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		
		return bookTransactionHistoryDao.count();
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(BookTransactionHistory entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends BookTransactionHistory> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends BookTransactionHistory> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookTransactionHistory> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookTransactionHistory> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends BookTransactionHistory> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
}
