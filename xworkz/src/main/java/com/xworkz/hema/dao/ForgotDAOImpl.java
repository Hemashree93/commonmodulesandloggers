package com.xworkz.hema.dao;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.xworkz.hema.controller.SignInController;
import com.xworkz.hema.entity.HomeEntity;

@Component
public class ForgotDAOImpl implements ForgotDAO{
	
	private static Logger logger = Logger.getLogger(SignInController.class);

	@Autowired
	private SessionFactory sf;
	
	
	public ForgotDAOImpl() {
		logger.info("created \t"+this.getClass().getSimpleName());
		
	}

	public HomeEntity fetchByEmail(String email, String password) {
		logger.info("invoked name");
		Session session=null;
		Transaction tx=null;
		
		try {
			session=sf.openSession();
			String hql="SEKECT RE FROM hOMEeNTITY SE WHERE email='" + email +"'";
			logger.info("email" +hql);
			Query query=session.getNamedQuery("updatePasswordByEmail");
			
			query.setParameter("password", password);
			query.setParameter("email", email);
			
			Object obj=query.executeUpdate();
			tx.commit();
			if(Objects.nonNull(obj)) {
				logger.info("Entity found"+obj);
			}else {
				logger.info("Entity not found");
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.info("INFO:" + e.getMessage());
		}finally {
			session.close();
		}
		return null;
	}

}
