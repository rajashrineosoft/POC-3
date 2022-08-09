package com.example.demo.services;

import java.util.List;
import com.example.demo.model.User;

public interface UserService {

	public List<User> getUser(); 

	public User addUser(User userDetails);

	public User updateUser(User userDetails);

	public String deleteUser(int userById);
	
	User getByFname(String firstName);
	
	List<User> softDeleteUser(int id);
	
	public List<User> findUserWithSorting(String field);

	
}
