package com.xworkz.hema.service;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.hema.dao.HomeDAO;
import com.xworkz.hema.dto.HomeDTO;
import com.xworkz.hema.entity.HomeEntity;

@Service
public class HomeServiceImpl implements HomeService {
	@Autowired
	private HomeDAO homeDAO;
	
	private static Logger logger = Logger.getLogger(HomeServiceImpl.class);


	public HomeServiceImpl() {
		logger.info("created \t" + this.getClass().getSimpleName());
	}

	public boolean Save(HomeDTO homeDTO) {
		boolean valid = false;
		try {
			logger.info("dataSave invoked " + homeDTO);

			if (Objects.nonNull(homeDTO)) {
				logger.info("starting validation for " + homeDTO);
				String UserName = homeDTO.getUserName();

				if (UserName != null && !UserName.isEmpty() && UserName.length() >= 3) {
					logger.info("UserName is valid");
					valid = true;
				} else {
					System.out.println("UserName is invalid");
					if (UserName == null) {
						logger.info("UserName is null");
					}
					if (UserName != null && UserName.length() < 3) {
						logger.info("name length is less than 5");
					}
					valid = false;
				}
				String email = homeDTO.getEmail();
				if (email != null && !email.isEmpty() && email.length() >= 5) {
					logger.info("email is valid");
					valid = true;
				} else {
					if (valid) {
						logger.info("email is invalid");
						valid = false;
					}
				}
				String mobile = homeDTO.getMobile();
				if (mobile != null && !mobile.isEmpty() && mobile.length() == 10) {
				logger.info("mobile is valid");
					valid = true;
				} else {
					if (valid) {
						logger.info("mobile is invalid");
						valid = false;

					}
				}

				String password = homeDTO.getPassword();
				if (password != null && !password.isEmpty() && password.length() >= 5) {
					logger.info("password is valid");
					valid = true;
				} else {
					if (valid) {
				logger.info("password is invalid");
						valid = false;

					}
				}
				String confirmpassword = homeDTO.getConfirmpassword();
				if (confirmpassword != null && !confirmpassword.isEmpty() && confirmpassword.length() >= 3) {
					logger.info("confirmpassword is valid");
					valid = true;
				} else {
					if (valid) {
					logger.info("confirmpassword is invalid");
						valid = false;

					}
				}
			}
			if (valid) {
				logger.info("Data is valid ,will convert  to entity");

				HomeEntity homeEntity = new HomeEntity();

				BeanUtils.copyProperties(homeDTO, homeEntity);
				HomeEntity homeEntity1 = homeDAO.fetchByemail(homeDTO.getEmail());

				if (homeEntity1 == null) {
					logger.info("entity is ready \t " + homeEntity);
					homeDAO.save(homeEntity);
					System.out.println("entity is saved");
				} else {
					logger.info("entity as saved email already exits" + homeDTO.getEmail());
					valid=false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return valid;

	}
}
