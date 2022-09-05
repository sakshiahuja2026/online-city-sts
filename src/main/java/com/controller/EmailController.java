package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.bean.EmailDetailsBean;
import com.service.EmailService;

@Controller
public class EmailController {
	 @Autowired
	 EmailService emailService;
	 
	    
	    public String
	    sendMail(@RequestBody EmailDetailsBean details)
	    {
	        String status
	            = emailService.sendSimpleMail(details);
	 
	        return status;
	    }
	 

	    public String sendMailWithAttachment(
	        @RequestBody EmailDetailsBean details)
	    {
	        String status
	            = emailService.sendMailWithAttachment(details);
	 
	        return status;
	    }
}