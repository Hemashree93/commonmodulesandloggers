package com.xworkz.hema.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.hema.dao.HomeDAOImpl;
import com.xworkz.hema.dto.SignInDTO;
import com.xworkz.hema.service.HomeService;
import com.xworkz.hema.service.SignInService;


@Controller
@RequestMapping("/")
public class SignInController {
	
	private static Logger logger = Logger.getLogger(SignInController.class);

	
	@Autowired
	private SignInService signInService;
	
	

	public SignInController() {
	 	logger.info("created \t"+this.getClass().getSimpleName());
	}
	
	@RequestMapping("/signin.do")
	public String onSignIn(SignInDTO signInDTO, ModelMap map) {
		try {
			logger.info("invoked onSignIn");
			boolean check=this.signInService.validateLoginDetails(signInDTO);
			
			
			if(check) {
				ModelMap success=map.addAttribute("successmessage", signInDTO.getEmail());
				return "last";
			}else {
				ModelMap failure=map.addAttribute("failureMessage","Signin unsuccessfull");
				logger.info("the email is not correct, enter the correct email");
				return "signin";
				}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("signin:"+e.getMessage());
		}
		return "signin";
	}
}

