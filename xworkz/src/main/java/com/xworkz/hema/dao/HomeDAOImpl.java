package com.xworkz.hema.dao;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.xworkz.hema.entity.HomeEntity;

@Component
public class HomeDAOImpl implements HomeDAO {
	private static Logger logger = Logger.getLogger(HomeDAOImpl.class);

	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public HomeDAOImpl() {
		logger.info(this.getClass().getSimpleName()+" obj created");
	}

	public void save(HomeEntity homeEntity) {
		logger.info("Invoking save method");
		Session session = null;
		try {

			logger.info("session created");
			session = factory.openSession();
			logger.info("Transaction begin");
			session.beginTransaction();
			logger.info("Entity saving...");
			session.save(homeEntity);
			logger.info("Commiting....");
			session.getTransaction().commit();
			logger.info("Data saved");

		} catch (NumberFormatException e) {
			e.printStackTrace();
			logger.info("INFO:" + e.getMessage());
		}finally {
			if (Objects.nonNull(session))
				session.close();
	}
	}

	
	public HomeEntity fetchByemail(String email) {

		Session session = factory.openSession();
		try {
			String syntax = "SELECT SE FROM HomeEntity SE WHERE SE.email ='" + email + "'";
			Query query = session.createQuery(syntax);
			Object result = query.uniqueResult();
			
			if(Objects.nonNull(result)) {
				logger.info("Entity is already exist");
				HomeEntity entity=(HomeEntity) result;
				return entity;
			}else {
				logger.info("Email is not duplicate,");
				return null;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			logger.info("Info: " + e.getMessage());
			session.close();
		}finally {
			if (Objects.nonNull(session))
				session.close();
		}
	
		return null;
		
	}
	public HomeEntity fetchByEmailandPassword(String email, String password) {
		Session session=factory.openSession();
		try {
			
			logger.info("invoke entity");
			
			Query query=session.getNamedQuery("fetchByEmailandPassword");
			query.setParameter("email", email);
			query.setParameter("password", password);
			logger.info("query " + query);
			Object result=query.uniqueResult();
			if(Objects.nonNull(result)) {
				logger.info("Entity found:"+email);
			HomeEntity entity=(HomeEntity) result;
			return entity;
			}
			else {
				logger.info("Entity not found");
				return null;
			}
	}catch(HibernateException e) {
		e.printStackTrace();
	
	}
	
	finally {
			if (Objects.nonNull(session))
				session.close();
			// close session

		}
		return null;

	}

	
}
