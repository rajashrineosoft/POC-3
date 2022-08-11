package com.neosoft.poc.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.neosoft.poc.model.User;
@Repository
public interface UserRepository extends MongoRepository<User,Integer>{

	User getByFirstName(String firstName);

}