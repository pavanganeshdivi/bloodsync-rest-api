package com.blood.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blood.DAO.UserDAO;
import com.blood.configuration.EmailConfiguration;
import com.blood.entity.UserDetails;
@Service
public class UserService {
	@Autowired
	UserDAO dao;
	@Autowired
	EmailConfiguration emailConfig;
	
	public ResponseEntity<String> insertData(UserDetails details){
		List<UserDetails> allUsers = dao.getAllUsers();
		if (allUsers.isEmpty()) {
			UserDetails data = dao.insertData(details);
			if(data!=null) {
				welcomeMail(data);
				return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successfull");
			}
		}
		else {
			boolean isPresent = false;
			for (UserDetails user : allUsers) {
				if (user.getEmailid().equals(details.getEmailid())) {
					isPresent=true;
					break;
				}
			}
			if(isPresent) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already Having Account");
			}
			else {
				UserDetails data = dao.insertData(details);
				if (data!=null) {
					welcomeMail(data);
					return ResponseEntity.status(HttpStatus.CREATED).body("Registration Successfull");
				}
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("An error occured");
	}
	
	public ResponseEntity<List<UserDetails>> getAllUsers() {
		
		List<UserDetails> users = dao.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	public ResponseEntity<UserDetails> getUser(String user,String password) {
		
		UserDetails userDetails = dao.getUser(user,password);
		if(userDetails != null)
			return ResponseEntity.status(HttpStatus.OK).body(userDetails);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public ResponseEntity<String> updateData(UserDetails details) {
		
		UserDetails updatedData = dao.updateData(details);
		if (updatedData!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Updated Successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Updation Failed");
	}
	
	public ResponseEntity<String> deleteUser(String email) {
		
		if (dao.deleteUser(email)!=0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Data deleted");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user found");
		}
	}
	
	public ResponseEntity<UserDetails> getUser(String email) {
		
		Optional<UserDetails> user = dao.getUser(email);
		if(!user.isEmpty())
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
	
	public ResponseEntity<String> mailToDonor(String email) {
		Optional<UserDetails> userDetails = dao.getUser(email);
		UserDetails user = userDetails.get();
		String body = "Dear " + user.getName() + ",\n\n" +
				"We hope you're doing well. We found your details on the BloodSync app and saw that you have the blood group " + user.getBloodGroup() + ".\n\n" +
				"There is an urgent need for this blood group in your area, " + user.getAddress() + ". Your donation could truly help save someone's life.\n\n" +
				"If you’re available and willing to donate, please let us know at your earliest convenience. Every drop counts! 🩸\n\n" +
				"Thank you for your kindness and support.\n\n" +
				"Warm regards,\n" +
				"BloodSync Team";
		boolean sendMail = emailConfig.sendMail(email, "Urgent Blood Requirement - BloodSync", body);
		if(sendMail)
			return ResponseEntity.status(HttpStatus.OK).body("Mail Sent Successfully");
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mail not delivered");
	}
	
	private void welcomeMail(UserDetails user) {
		String subject = "Welcome to BloodSync – Registration Successful!";
        String body = "Dear " + user.getName() + ",\n\n"
            + "Thank you for registering with BloodSync – a platform dedicated to saving lives through voluntary blood donation.\n\n"
            + "Your account has been successfully created, and you are now part of a growing community of life-savers.\n\n"
            + "You can now:\n"
            + "🩸 Offer help by marking your availability\n"
            + "🔍 Search for donors in your area\n"
            + "📬 Receive urgent blood requests\n\n"
            + "We’re excited to have you with us!\n\n"
            + "Stay safe,\n"
            + "Team BloodSync\n\n"
            + "\"Donate blood, save lives – one drop at a time.\"";
        emailConfig.sendMail(user.getEmailid(), subject, body);
	}
	
}