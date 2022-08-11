package com.neosoft.poc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.poc.Repository.UserRepository;
import com.neosoft.poc.model.User;
import com.neosoft.poc.services.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	    @Autowired(required=true)
		private UserService userService;
		
	    public UserController(UserService userService) {
			super();
			this.userService = userService;
		}
	    
	    @Autowired
	    private UserRepository userRepository;
	    
	    //Add new user into database
		@PostMapping("/addUser")
		public User addUser(@Valid @RequestBody User newUser) {
			return userService.addUser(newUser);
		}
		
		//Fetch all users from database
		@GetMapping("/getAllUserDetails")
		public Iterable<User> getUser() {
			return userService.getUser();
		}
		
		//Update from user database
		@PutMapping("/updateUser/{id}")
		public User updateUser(@Valid @RequestBody User newUser,@PathVariable("id")int id) {
			return userService.updateUser(newUser);
		}
		
		
	   //Delete user based on id(Hard Delete)
		  @DeleteMapping("/DeleteUser/{userById}")
     		public ResponseEntity<String>  deleteUserById(@PathVariable("userById")int userById) {
				return new ResponseEntity<String>(userService.deleteUser(userById),HttpStatus.OK);
			}
			
		//Search user based on first name
			@GetMapping("/searchUserByFirstName/{firstName}")
			public ResponseEntity<User> getUser(@PathVariable("firstName") String firstName) {
            User user = this.userService.getByFname(firstName);
                  return new ResponseEntity<User>(user, HttpStatus.OK);
			}
				
	  // Sort Data based on field
        @GetMapping("/{field}")
		public Iterable<User> getUserWithSort(@PathVariable String field){
			Iterable<User>users=userService.findUserWithSorting(field);
			return userService.findUserWithSorting(field);
		}
		
      //soft Delete user 
      @DeleteMapping("/softDeleteUser/{userId}")
      public  String softDeleteUser(@PathVariable ("userId")int userId) {
		 userService.softDeleteUser(userId);
		return  "record deleted successfully...";
		}
		
}	

