package com.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.models.Book;
import com.models.User;

/**
 * @author RITESH SINGH
 *
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	@Qualifier("mongoTemplate")
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private UserDao userDao;

	@Override
	public <S extends User> List<S> save(Iterable<S> entites) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		
		try{
			Query query = new Query();
			query.with(new Sort(Sort.Direction.DESC, "_id"));
			List<User> users=  mongoTemplate.find(query, User.class);
			return users;
		}catch(Exception ee){}
		return null;
	}

	@Override
	public List<User> findAll(Sort sort) {
		
		try{
			Query query = new Query();
			query.with(sort);
			List<User> users=  mongoTemplate.find(query, User.class);
			return users;
		}catch(Exception ee){}
		return null;
	}

	@Override
	public <S extends User> S insert(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> insert(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		
		return userDao.findAll(pageable);
	}

	@Override
	public <S extends User> S save(S entity) {
		
		return userDao.save(entity);
	}

	@Override
	public User findOne(String id) {
		
		return userDao.findOne(id);
	}

	@Override
	public boolean exists(String id) {
		
		return userDao.exists(id);
	}

	@Override
	public Iterable<User> findAll(Iterable<String> ids) {
		
		return userDao.findAll(ids);
	}

	@Override
	public long count() {
		
		return userDao.count();
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Iterable<? extends User> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends User> S findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends User> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends User> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUserByUserName(String userName) {
		
		return userDao.findUserByUserName(userName);
	}
}
