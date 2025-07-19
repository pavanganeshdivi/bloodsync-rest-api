package com.blood.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDetails {
	String name;
	@Id
	String emailid;
	long mobile;
	String password;
	LocalDate dob;
	String bloodGroup;
	String address;
	String availability;
}