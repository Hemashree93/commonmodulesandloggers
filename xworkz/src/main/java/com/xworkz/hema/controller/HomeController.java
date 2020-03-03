package com.xworkz.hema.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.hema.dao.HomeDAOImpl;
import com.xworkz.hema.dto.HomeDTO;
import com.xworkz.hema.entity.HomeEntity;
import com.xworkz.hema.service.HomeService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private HomeService homeService;
	
	private static Logger logger = Logger.getLogger(HomeController.class);


	public HomeController() {
		logger.info("created \t" + this.getClass().getSimpleName());
	}

	@RequestMapping("/Sumbit.do")
	public String onSubmit(HomeDTO homeDTO, HomeEntity homeEntity, ModelMap map) {
		try {
			logger.info("invoked onSubmit");
			logger.info(homeDTO);
			boolean check = this.homeService.Save(homeDTO);
			if (check) {
				map.addAttribute("name", homeDTO.getUserName());
				map.addAttribute("xyz", "Data saved Successfully");
			} else {
				map.addAttribute("abc", "Data not saved");
			}
			// System.out.println(homeDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "signup";

	}
}
