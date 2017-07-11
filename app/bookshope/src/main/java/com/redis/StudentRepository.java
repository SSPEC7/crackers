package com.redis;

import java.util.Map;

public interface StudentRepository {

	void save(Student student);

	void update(Student student);

	Student findStudent(String id);

	Map<Object, Object> findAllStudents();
}
