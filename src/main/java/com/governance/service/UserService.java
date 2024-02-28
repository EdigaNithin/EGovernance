package com.governance.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.governance.DTO.UserDTO;
import com.governance.entity.User;
import com.governance.exception.CustomException;
import com.governance.exception.PasswordMissmatchException;
import com.governance.exception.UserNotFound;
import com.governance.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	public User createUser(@Valid UserDTO user) throws CustomException {
		if(userRepo.existsByEmail(user.getEmail())) {
			throw new CustomException("User is Already Exists");
		}
		User u1=new User();
		u1.setName(user.getName());
		u1.setEmail(user.getEmail());
		u1.setPassword(user.getPassword());
		return userRepo.save(u1);
	}
	
	public User loginUser(@Valid String email,String password ) throws UserNotFound, PasswordMissmatchException {
		User user=userRepo.findByEmail(email.toLowerCase()).orElseThrow(()->new UserNotFound("User is not found"));
		if(!user.getPassword().equals(password)) {
			throw new PasswordMissmatchException("invalid PassWord");
		}
		return user;
	}
	


    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }
}
