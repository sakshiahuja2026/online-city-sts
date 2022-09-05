package com.bean;

import lombok.Data;

@Data
public class ForgotPasswordBean {
	private String email;
	private Integer otp;
}
