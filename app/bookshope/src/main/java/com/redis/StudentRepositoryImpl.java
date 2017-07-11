package com.redis;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class StudentRepositoryImpl implements StudentRepository {
 
    private static final String KEY = "Student";
     
    private RedisTemplate<String, Student> redisTemplate;
    private HashOperations hashOps;
 
    @Autowired
    private StudentRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
 
    @PostConstruct
    private void init() {
        hashOps = redisTemplate.opsForHash();
    }
     
    @Override
    public void save(Student student) {
        hashOps.put(KEY, student.getId(), student);
    }
 
    @Override
    public void update(Student student) {
        hashOps.put(KEY, student.getId(), student);
    }
 
    @Override
    public Student findStudent(String id) {
        return (Student) hashOps.get(KEY, id);
    }
 
    @Override
    public Map<Object, Object> findAllStudents() {
        return hashOps.entries(KEY);
    }
 
    public void deleteStudent(String id) {
        hashOps.delete(KEY, id);
    }
}