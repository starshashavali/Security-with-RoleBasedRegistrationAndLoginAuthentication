package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, Integer> {

	UserEntity findByUserEmail(String email);

	UserEntity findByUserEmailAndUserPwd(String username, String pwd);
	

}
