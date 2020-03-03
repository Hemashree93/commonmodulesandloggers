package com.xworkz.hema.service;

import com.xworkz.hema.dto.ForgotDTO;

public interface ForgotService {
	public boolean validateForgotPassword(ForgotDTO dto);
	public String validateForgot(ForgotDTO dto);

}
