package com.service;

import com.bean.EmailDetailsBean;

public interface EmailService {
	 	String sendSimpleMail(EmailDetailsBean details);
	 
	    // Method
	    // To send an email with attachment
	    String sendMailWithAttachment(EmailDetailsBean details);
}