package com.xworkz.hema.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.xworkz.hema.dto.ForgotDTO;
import com.xworkz.hema.service.ForgotService;
import com.xworkz.hema.service.HomeService;

public class ForgotController {
	
	@Autowired
	private ForgotService forgotService;
	
	private static Logger logger = Logger.getLogger(HomeController.class);
	
	public ForgotController() {
		logger.info("created \t"+this.getClass().getSimpleName());
	}
	
	public String onForgotPassword(ForgotDTO dto, ModelMap map) {

		try {
			logger.info("invoked onForgotPassword");
			logger.info(dto);
			String pass=this.forgotService.validateForgot(dto);
			boolean valid = this.forgotService.validateForgotPassword(dto);
			if (valid) {
			ModelMap complete=map.addAttribute("email", dto.getEmail());
			ModelMap complete1=map.addAttribute("password", dto.getPassword());
			ModelMap complete2=map.addAttribute("confirmPassword", dto.getConfirmPassword());
			} else {
				ModelMap failure=map.addAttribute("failure", "Data not saved forgotPassword not successfull");
			}
			logger.info(dto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forgotPassword";
	}
}
