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

import com.models.BookAccount;

/**
 * @author RITESH SINGH
 *
 */
@Repository("bookAccountRepository")
public class BookAccountRepositoryImpl implements BookAccountRepository {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private BookAccountDao bookAccountDao;

	@Override
	public <S extends BookAccount> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookAccount> findAll() {
		try{
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "_id"));
			List<BookAccount> bookAccounts=  mongoTemplate.find(query, BookAccount.class);
			return bookAccounts;
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public List<BookAccount> findAll(Sort sort) {
		try{
			Query query = new Query();
			query.with(sort);
			List<BookAccount> bookAccounts=  mongoTemplate.find(query, BookAccount.class);
			return bookAccounts;
		}catch(Exception ee){}
		
		return null;
	}

	@Override
	public <S extends BookAccount> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookAccount> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookAccount> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookAccount> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BookAccount> findAll(Pageable pageable) {
		
		return bookAccountDao.findAll(pageable);
	}

	@Override
	public <S extends BookAccount> S save(S entity) {
		return bookAccountDao.save(entity);
	}

	@Override
	public BookAccount findOne(String id) {
		return bookAccountDao.findOne(id);
	}

	@Override
	public boolean exists(String id) {
		return bookAccountDao.exists(id);
	}

	@Override
	public Iterable<BookAccount> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		return bookAccountDao.count();
	}

	@Override
	public void delete(String id) {
		bookAccountDao.delete(id);
	}

	@Override
	public void delete(BookAccount entity) {
		bookAccountDao.delete(entity);	
	}

	@Override
	public void delete(Iterable<? extends BookAccount> entities) {
		
	}

	@Override
	public void deleteAll() {
		
	}

	@Override
	public <S extends BookAccount> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookAccount> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BookAccount> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends BookAccount> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
}
