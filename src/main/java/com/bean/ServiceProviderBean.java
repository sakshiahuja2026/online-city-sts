package com.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "serviceprovider")
@Data
public class ServiceProviderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer serviceProviderId; 
	
	@NotBlank(message = "Please enter First Name")
	private String firstName;

	@NotBlank(message = "Please enter Last Name")
	private String lastName;
	
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
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
	
	@NotBlank(message = "Please insert photo")
	private String imgUrl;
	
	private String service;
		
	
	}
	

