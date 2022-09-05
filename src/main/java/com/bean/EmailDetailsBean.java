package com.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class EmailDetailsBean {
	private String recipient;
	private String msgBody;
	private String subject;
	private String attachment;
	
	
}