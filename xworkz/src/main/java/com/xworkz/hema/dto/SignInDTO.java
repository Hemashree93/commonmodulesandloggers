package com.xworkz.hema.dto;

import org.apache.log4j.Logger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignInDTO {
	private String email;
	private String password;
	private static Logger logger = Logger.getLogger(SignInDTO.class);

	
	public SignInDTO() {
	logger.info("created \t"+this.getClass().getSimpleName());
	}

	@Override
	public String toString() {
		return "SignInDTO [Email=" + email + ", Password=" + password + "]";
	}
	
	

}

