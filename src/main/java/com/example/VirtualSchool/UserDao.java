package com.example.VirtualSchool;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



	public interface UserDao extends JpaRepository<User, Long>{

		User findByUsername(String name);
		
		 User findUserById(Long id);
		
		List<User> findAllById(Long id);
		
		
		

}
