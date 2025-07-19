package com.blood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blood.entity.UserDetails;
import com.blood.service.UserService;
@CrossOrigin(origins = "*")
@RestController
public class UserController {
	@Autowired
	UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> registration(@RequestBody UserDetails user){
		return service.insertData(user);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<UserDetails>> getUsers(){
		return service.getAllUsers();
	}
	
	@GetMapping("/login/{email}/{password}")
	public ResponseEntity<UserDetails> getUser(@PathVariable("email") String email,@PathVariable("password") String password){
		return service.getUser(email,password);
	}
	
	@GetMapping("/user/{email}")
	public ResponseEntity<UserDetails> getUser(@PathVariable("email") String email) {
		return service.getUser(email);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> updation(@RequestBody UserDetails user) {
		return service.updateData(user);
	}
	
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable("email") String email) {
		return service.deleteUser(email);
	}
	
	@GetMapping("/mailToDonor/{email}")
	public ResponseEntity<String> mailToDonor(@PathVariable("email") String email) {
		return service.mailToDonor(email);
	}
}
