package com.xworkz.hema.dto;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.xworkz.hema.dao.HomeDAOImpl;

import lombok.Data;

@Component
@Data
public class ForgotDTO {
	
	private String email;
	private String password;
	private String confirmPassword;
	private static Logger logger = Logger.getLogger(ForgotDTO.class);

	
	public ForgotDTO() {
		
		logger.info("created \t" + this.getClass().getSimpleName());
	}

}
