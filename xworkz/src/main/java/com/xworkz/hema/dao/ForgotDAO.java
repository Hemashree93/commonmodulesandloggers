package com.xworkz.hema.dao;

import com.xworkz.hema.entity.HomeEntity;

public interface ForgotDAO {
	public HomeEntity fetchByEmail(String email, String password);

}
