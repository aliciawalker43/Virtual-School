package com.example.VirtualSchool;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>{

		User findByUsername(String name);
		
		
		
		User findByGoogleId(Long googleId);
		
		

}
