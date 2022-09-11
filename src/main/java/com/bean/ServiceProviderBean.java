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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name="service_provider")
@Data
public class ServiceProviderBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer providerId;
	
	private String firstName;
	private String lastName;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="dd-mm-yyyy")
	private Date Dob;
	private String gender;
	private String email;
	private Integer phone;
	private String password;
	private String pincode;
	private String service;
//	private String authToken;
	@Transient
	MultipartFile photo;
	String profilepath;
	private String isActive;
	
	@ManyToOne
	@JoinColumn(name = "roleId", nullable = false)
	RoleBean role;
}