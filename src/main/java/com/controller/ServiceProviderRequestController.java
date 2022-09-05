package com.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.ServiceProviderRequestBean;
import com.bean.UserBean;
import com.repository.ImageRepository;
import com.repository.ServiceProviderRequestRepository;
import com.service.TokenService;

@CrossOrigin
@RestController
public class ServiceProviderRequestController {

	@Autowired
	ServiceProviderRequestRepository providerRequestRepository;
	@Autowired
	BCryptPasswordEncoder bCrypt;
	@Autowired
	TokenService tokenService;

	@PostMapping("/signupserviceprovider")
	public ResponseEntity<ResponseBean<ServiceProviderRequestBean>> signup(
			@RequestBody ServiceProviderRequestBean serviceprovider) {
		ServiceProviderRequestBean provider = (ServiceProviderRequestBean) providerRequestRepository.findByEmail(serviceprovider.getEmail());
		System.out.println("provider printed" + provider);
		ResponseBean<ServiceProviderRequestBean> res = new ResponseBean<>();

		if (provider == null) {

			String encPassword = bCrypt.encode(serviceprovider.getPassword());
			serviceprovider.setPassword(encPassword);
			providerRequestRepository.save(serviceprovider);

			res.setMsg("Signup done...");

			return ResponseEntity.ok(res);
		} else {
			res.setData(serviceprovider);
			res.setMsg("Email Already Taken");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}

@PostMapping("/loginserviceprovider")
public ResponseEntity<?> authenticate(@RequestBody LoginBean login) {
		ServiceProviderRequestBean provider = (ServiceProviderRequestBean) providerRequestRepository.findByEmail(login.getEmail());
	
	if(provider == null || bCrypt.matches(login.getPassword(), provider.getPassword())==false) {
		ResponseBean<LoginBean> res = new ResponseBean<>();
		res.setData(login);
		res.setMsg("Invalid Credentials");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
	}
	else {
		
			String authToken = tokenService.generateToken(16);
			provider.setAuthToken(authToken);
			providerRequestRepository.save(provider);
			return null;
		}
	
	}


//@RequestMapping(path = "check")



    @Autowired
   ImageRepository imageRepository;

    @PostMapping("/upload")
    public ServiceProviderRequestBean uplaodImage(@RequestParam("myFile") MultipartFile file) throws IOException {

        ServiceProviderRequestBean img = new ServiceProviderRequestBean( file.getOriginalFilename(),file.getContentType(),file.getBytes() );


        final ServiceProviderRequestBean savedImage = imageRepository.save(img);


        System.out.println("Image saved");


        return savedImage;


    }
}


