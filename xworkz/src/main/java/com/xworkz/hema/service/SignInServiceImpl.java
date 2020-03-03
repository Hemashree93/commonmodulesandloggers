package com.xworkz.hema.service;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.hema.dao.HomeDAO;
import com.xworkz.hema.dto.SignInDTO;
import com.xworkz.hema.entity.HomeEntity;

@Service
public class SignInServiceImpl implements SignInService {
	
	private static Logger logger = Logger.getLogger(SignInServiceImpl.class);


	@Autowired
	private HomeDAO dao;

	public SignInServiceImpl() {
		logger.info("created \t" + this.getClass().getSimpleName());
	}

	public boolean validateLoginDetails(SignInDTO signInDTO) {
		boolean valid = false;
		try {
			logger.info("dataSave invoked " + signInDTO);

			if (Objects.nonNull(signInDTO)) {

				HomeEntity home1 = dao.fetchByEmailandPassword(signInDTO.getEmail(), signInDTO.getPassword());

				if (home1 != null) {
					logger.info("entity is ready \t " + home1);
					
				logger.info("Email is exist");
					valid = true;
				}
			} else {
				logger.info("entity as saved email already exits");
				valid= false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;

	}
}
