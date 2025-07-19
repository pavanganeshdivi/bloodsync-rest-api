package com.blood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blood.entity.UserDetails;

import jakarta.transaction.Transactional;
import java.util.List;


public interface UserRepository extends JpaRepository<UserDetails, String> {
	@Transactional
	int removeByEmailid(String emailid);
	
	UserDetails findByEmailidAndPassword(String emailid,String password);
}
