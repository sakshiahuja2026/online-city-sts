package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public interface UserRepository extends JpaRepository<UserBean, Integer> {

	UserBean findByEmail(String email);

	UserBean findByUserId(Integer userId);
	
}
