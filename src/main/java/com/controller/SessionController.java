package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmailDetailsBean;
import com.bean.ForgotPasswordBean;
import com.bean.LoginBean;
import com.bean.ResponseBean;
import com.bean.RoleBean;
import com.bean.UserBean;
import com.repository.RoleRepository;
import com.repository.ServiceProviderRepository;
import com.repository.UserRepository;
import com.service.OtpService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/public")
@CrossOrigin

public class SessionController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCrypt;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	OtpService otpService;
	@Autowired
	EmailController emailController;

	@PostMapping("/signup")
	public ResponseEntity<ResponseBean<UserBean>> signup(@RequestBody UserBean userBean) {
		UserBean dbUser = userRepository.findByEmail(userBean.getEmail());
		// System.out.println("dbUser printed" + dbUser);
		ResponseBean<UserBean> res = new ResponseBean<>();

		if (dbUser == null) {
			// System.out.println(dbUser.getGender());
			RoleBean role = roleRepository.findByRoleName("user");
			userBean.setRole(role);
			String encPassword = bCrypt.encode(userBean.getPassword());
			userBean.setPassword(encPassword);
			userRepository.save(userBean);
			return ResponseEntity.ok(res);

		}

		else {
			res.setData(userBean);
			res.setMsg("Email Already Taken");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticate(@RequestBody LoginBean login) {
		System.out.println("login called.......");

		UserBean dbUser = userRepository.findByEmail(login.getEmail());
		System.out.println("dbuser...." + dbUser.getEmail());
		if (dbUser == null || bCrypt.matches(login.getPassword(), dbUser.getPassword()) == false) {
			ResponseBean<LoginBean> res = new ResponseBean<>();
			res.setData(login);
			res.setMsg("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
		} else {

			userRepository.save(dbUser);
			// log.info("AuthToken generated");

			ResponseBean<Map<String, Object>> res = new ResponseBean<>();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("user", dbUser);
			res.setData(data);
			res.setMsg("Login done...");
			return ResponseEntity.ok(res);
		}
	}

	@GetMapping("/getallUsers")
	public ResponseEntity<?> getallUsers(UserBean user) {
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<ResponseBean<UserBean>> removeUser(@PathVariable("userId") Integer userId) {
		System.out.println("delete user");
		UserBean user = userRepository.findByUserId(userId);
		ResponseBean<UserBean> res = new ResponseBean<>();

		if (user == null) {
			res.setMsg("invalid");
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		} else {
			userRepository.deleteById(userId);
			res.setData(user);
			System.out.println("user delete "+user.getUserId());
			res.setMsg("user removed");
			return ResponseEntity.ok(res);
		}
	}

	@PostMapping("/otpsend")
	public ResponseEntity<?> sendotp(@RequestBody LoginBean login) {
		EmailDetailsBean emailBean = new EmailDetailsBean();
		String email = login.getEmail();
		UserBean userBean = userRepository.findByEmail(email);
		Integer otp = otpService.genrateOtp();
		emailBean.setRecipient(email);
		emailBean.setSubject("forget password otp");
		emailBean.setMsgBody("forgot password OTP is-" + otp);
		emailController.sendMail(emailBean);
		userBean.setOtp(otp);
		userRepository.save(userBean);
		return ResponseEntity.ok(emailBean);
	}

	@PostMapping("/otp")
	public ResponseEntity<?> forgot(@RequestBody ForgotPasswordBean forgotpassword) {
		ResponseBean<Object> res = new ResponseBean<>();
		String email = forgotpassword.getEmail();
		UserBean userBean = userRepository.findByEmail(email);
		Integer otp = userBean.getOtp();
		if (otp == null) {
			res.setData(email);
			res.setMsg("otp not found");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
		} else if (otp.equals(forgotpassword.getOtp())) {
			res.setData(email);
			System.out.println("email printed" + email);
			res.setMsg("successfully...");
			userBean.setOtp(null);
			userRepository.save(userBean);
			return ResponseEntity.ok(res);
		} else {
			res.setData(email);
			res.setMsg("incorrect otp");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
		}
	}

	@PostMapping("/forgot")
	public ResponseEntity<?> updatepassword(@RequestBody LoginBean login) {
		ResponseBean<Object> res = new ResponseBean<>();
		UserBean userBean = userRepository.findByEmail(login.getEmail());
		System.out.println(login.getEmail());
		userBean.setPassword(bCrypt.encode(login.getPassword()));
		System.out.println(login.getPassword());
		userRepository.save(userBean);
		res.setData(userBean);
		res.setMsg("password successfully forgot...");
		return ResponseEntity.ok(res);

	}
}
