package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.bean.UserBean;

public interface UserRepository extends CrudRepository<UserBean, Integer> {

	UserBean findByEmail(String email);
	UserBean findByAuthToken(String authToken);

}
