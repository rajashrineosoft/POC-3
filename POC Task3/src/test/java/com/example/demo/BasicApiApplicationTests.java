package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.neosoft.poc.Repository.UserRepository;
import com.neosoft.poc.model.User;



@SpringBootTest
class BasicApiApplicationTests {

	@Autowired
	UserRepository userRepository;
	
	/* Test case for Save User (Post method) */
	@Test
	@Order(1)
 public void testSaveNewUser() throws ParseException {
	User user=new User();
	user.setId(1);
	user.setFirstName("reyansh");
	user.setLastName("dorge");
	user.setPincode("412028");
	user.setDateOfBirth("18-05-2005");
	user.setJoiningDate("28-07-2112");
	userRepository.save(user);
	assertNotNull(userRepository.findById(1).get());
	
	}
	
	/* Test case for Fetch All User (Get method) */
   @Test
   @Order(2)
   public void testReadAllUserDetails()
   {
	   List<User>list= userRepository.findAll() ;
	   assertThat(list).size().isGreaterThan(0);
 }
   
	/* Test case for Fetch Single User (Get method) */
   @Test
   @Order(3)
   public void testSingleUserDetails()
   {
	User user=userRepository.findById(1).get();
	assertEquals("reyansh", user.getFirstName());
   }
   
   /* Test case for search User  */
   @Test
   @Order(5)
   public void testSearchUserDetails()
   {
	User user=userRepository.findById(1).get();
	assertEquals("reyansh", user.getFirstName());
   }
   
	/* Test case for Update User (Put method) */
   @Test
   @Order(4)
   public void testUpdateUser()
   {
	 User user=  userRepository.findById(1).get();
	   user.setFirstName("reyansh");
	   userRepository.save(user);
	   assertNotEquals("manali", userRepository.findById(1).get().getFirstName());
   }
   
	/* Test case for Delete User (Delete method) */
   @Test
   @Order(5)
   public void testDeleteUser()
 {
	userRepository.deleteById(2);
	assertThat(userRepository.existsById(2)).isFalse();
 }
	
   /* Test case for Sort User  */
   @Test
   @Order(6)
   public void sortingByMultipleFields(){
       String sortBy = "dateOfBirth";
       String sortByDesc = "joiningDate";
       String sortDirection = "desc";

       Sort sortBydateOfBirth = sortDirection.equals(Sort.Direction.ASC.name())?
               Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();

       Sort sortByjoiningDate = sortDirection.equals(Sort.Direction.ASC.name())?
               Sort.by(sortByDesc).ascending(): Sort.by(sortByDesc).descending();

       Sort groupBySort = sortBydateOfBirth.and(sortByjoiningDate);

       List<User> users = userRepository.findAll(groupBySort);

       users.forEach((u) ->{
           System.out.println(u);
       });
   }	
}


