package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ServiceProviderBean;
import com.repository.ServiceProviderRepository;

import lombok.extern.log4j.Log4j2;

@CrossOrigin
@RequestMapping("/search")
@RestController
@Log4j2
public class SearchController {

	@Autowired
	ServiceProviderRepository serviceProviderRepository;

	@GetMapping("/searchService/{pincode}/{service}")
	public ResponseEntity<?> searchHospital(@PathVariable("pincode") String pincode,
			@PathVariable("service") String service) {
		ServiceProviderBean serviceProviderBean = new ServiceProviderBean();
		serviceProviderBean.setPincode(pincode);
		serviceProviderBean.setService(service);
		return ResponseEntity.ok(serviceProviderRepository.findByPincodeAndService(serviceProviderBean.getPincode(),
				serviceProviderBean.getService()));

	}

//	@GetMapping("/searchMechanic/{mechanic}")
//	public ResponseEntity<?> searchMechanic(ServiceProviderBean serviceProviderBean){
//		return ResponseEntity.ok(serviceProviderRepository.findByPincodeAndService(serviceProviderBean.getPincode(),serviceProviderBean.getService())) ;
//		
//	}
}