package com.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "service_user")

public class UserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	@ManyToOne
	@JoinColumn(name = "roleId", nullable = false)
	RoleBean role;

//	String authToken;
	private Integer otp;
	

	@NotBlank(message = "Please enter First Name")
	private String firstName;

	@NotBlank(message = "Please enter Last Name")
	private String lastName;

	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date Dob;

	@NotBlank(message = "Please enter Gender")
	private String gender;

	@NotBlank(message = "Please enter Email")
	private String email;

	@NotBlank(message = "Please enter Phone number")
	private String phone;

	@NotBlank(message = "Please enter password")
	private String password;

	private Integer pincode;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public RoleBean getRole() {
		return role;
	}

	public void setRole(RoleBean role) {
		this.role = role;
	}

//	public String getAuthToken() {
//		return authToken;
//	}
//
//	public void setAuthToken(String authToken) {
//		this.authToken = authToken;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public Integer getOtp() {
		return otp;
	}

	public void setOtp(Integer otp) {
		this.otp = otp;
	}

}