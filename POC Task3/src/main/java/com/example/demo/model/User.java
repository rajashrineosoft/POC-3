package com.example.demo.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Document(collection="userdetails")


public class User {
	
	public User()
	{
		
	}
	private int id;
    @NotEmpty
	@Size(min = 4, message = "First Name must contain atleast 4 characters")
	private String firstName;
    @NotEmpty
	@Size(min = 4, message = "Last Name must contain atleast 4 characters")
	private String lastName;
    @NotEmpty
	@Size(min = 6, max = 6, message = "Pin Code must contain 6 digits")
	private String pincode;
    @NotNull(message = "Must Enter Birth Date")
	private String dateOfBirth;
    @NotNull(message = "Must Enter Joining Date")
	private String joiningDate;
    
    private boolean isActive=true;

}