package com.aerospike.models;


import java.util.Date;
import java.util.Set;

import org.springframework.data.aerospike.mapping.Field;
import org.springframework.data.annotation.Id;

public class Person {

	@Id
	private String id;
	private String firstname;
    private String lastName;
    private String email;
    @Field(value="age")
    private Integer age;
    Date createdAt;
    @Field(value="ShipAddresses")
    private Set<String> shippingAddresses;
    
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Set<String> getShippingAddresses() {
		return shippingAddresses;
	}
	public void setShippingAddresses(Set<String> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
