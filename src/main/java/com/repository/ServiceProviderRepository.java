package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bean.ServiceProviderBean;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProviderBean, Integer> {
	ServiceProviderBean findByEmail(String email);
	ServiceProviderBean findByProviderId(Integer providerId);
	List<ServiceProviderBean> findByPincodeAndService(String pincode,String service);
	ServiceProviderBean  findByIsActive(String isActive);
	ServiceProviderBean findByIsActive(Integer providerId);
	
	
}
