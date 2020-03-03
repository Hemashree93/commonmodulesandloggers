package com.xworkz.hema.service;

import java.util.Objects;



import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.hema.controller.HomeController;
import com.xworkz.hema.dao.ForgotDAO;
import com.xworkz.hema.dto.ForgotDTO;
import com.xworkz.hema.entity.HomeEntity;

@Service
public class ForgotServiceImpl  {
	private static Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private ForgotDAO dao;
	
	public ForgotServiceImpl() {
		logger.info("created \t"+this.getClass().getSimpleName());
	}
	public boolean validateForgotPassword(ForgotDTO dto) {
		boolean valid=false;
		try {
			logger.info("dataSave invoked " + dto);

			if (Objects.nonNull(dto)) {
				logger.info("starting validation for " + dto);
				String email = dto.getEmail();

				if (email != null && !email.isEmpty() && email.length() >= 10) {
					logger.info("email is valid");
					valid = true;
					
					String password = dto.getPassword();

					if (valid&&password != null && !password.isEmpty() && password.length() >= 3) {
						logger.info("password is valid");
						valid = true;
						
				String confirmPassword = dto.getConfirmPassword();

					if (valid&&confirmPassword != null && !confirmPassword.isEmpty() && confirmPassword.length() >= 3) {
						logger.info("confirPassword is valid");
						valid = true;
						
						
					} else {
						
						logger.info("confirmpassword is invalid");
							valid = false;

						}
					}else {
						
							logger.info("password is invalid");
									valid = false;
					}
						} else {
					logger.info("email is invalid");
					valid = false;
						}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;

	}

	public String validateForgot(ForgotDTO dto) {
		HomeEntity entity =new HomeEntity();
	
		HomeEntity entity1=dao.fetchByEmail(dto.getEmail(),dto.getPassword());
		if(Objects.nonNull(entity1)){
			logger.info("Email exist"+entity1);
		}else {
			logger.info("Email doesnot exist");
		}
		return null;
		
		
	}}



