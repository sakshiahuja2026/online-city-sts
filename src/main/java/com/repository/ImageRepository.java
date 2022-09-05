package com.repository;  
import org.springframework.data.jpa.repository.JpaRepository;

import com.bean.ServiceProviderRequestBean;

public interface ImageRepository extends JpaRepository<ServiceProviderRequestBean, Long> {

	ServiceProviderRequestBean save(ServiceProviderRequestBean img);
}