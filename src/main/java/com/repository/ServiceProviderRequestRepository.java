package com.repository;

import org.springframework.data.repository.CrudRepository;

import com.bean.ServiceProviderRequestBean;
import com.bean.UserBean;


public interface ServiceProviderRequestRepository extends CrudRepository<ServiceProviderRequestBean, Integer>{
	ServiceProviderRequestRepository  findByEmail(String email);

	UserBean findByAuthToken(String authToken);
}
