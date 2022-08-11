package com.neosoft.poc.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.neosoft.poc.Repository.UserRepository;
import com.neosoft.poc.model.User;
import com.neosoft.poc.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	/* Method for fetch all data */	
	@Override
	public List<User> getUser() {
		
		List<User> userList=userRepository.findAll();
		return userList;
	}

	/* Method for add new user */	
	@Override
	public User addUser(User newUser) {
		return userRepository.save(newUser);
	}

	/* Method for Update user */	
	@Override
	public User updateUser(User newUser) {
		return userRepository.save(newUser);
	}

	 /* Method for Soft Delete user */
	  @Override
		public List<User>softDeleteUser(int id)
		{
			User userDetails=userRepository.findById(id).get();
			userDetails.setActive(false);
			userRepository.save(userDetails);
			List<User>userlist=userRepository.findAll();
			List<User>list=new ArrayList<>();
			for(User user:userlist)
			{
				if(user.isActive()==true)
				{
					list.add(userDetails);
				}
			}
			return list;
		}
		
	  /* Method for Hard Delete user */	
   @Override
	public String deleteUser(int userById) {
		userRepository.deleteById(userById);
		return "Data Deleted Successfully";
		
	}
   
   /* Method for Search user */	
   @Override
	public User getByFname(String firstName) {
	return userRepository.getByFirstName(firstName);
	}
   
   /* Method for Sort user */	
   @Override
	public List<User> findUserWithSorting(String field) {
		return userRepository.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	
}