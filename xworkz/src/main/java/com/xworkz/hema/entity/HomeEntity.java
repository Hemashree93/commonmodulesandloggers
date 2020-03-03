package com.xworkz.hema.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.xworkz.hema.dto.SignInDTO;

import lombok.Data;

@Component
@Entity
@Table(name="xtask")
@Data
@NamedQueries({@NamedQuery(name="fetchByemail", query="select sign from HomeEntity sign where sign.email=email"),
@NamedQuery(name="fetchByEmailandPassword", query="select sign from HomeEntity sign where sign.email=:email and sign.password=:password")})
public class HomeEntity implements Serializable {
	@Id
	@GenericGenerator(name = "auto", strategy = "increment")
	@GeneratedValue(generator = "auto")
	@Column(name = "id")
	private int id;
	@Column(name = "userName")
	private String userName;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile")
	private String mobile;
	@Column(name = "password")
	private String password;
	@Column(name = "confirmpassword")
	private String confirmpassword;
	
	private static Logger logger = Logger.getLogger(HomeEntity.class);

	
	public HomeEntity() {
	logger.info("created \t"+this.getClass().getName() );
	}

	public HomeEntity(int id, String userName, String email, String mobile, String password, String confirmpassword) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.mobile = mobile;
		this.password = password;
		this.confirmpassword = confirmpassword;
	}
	
	

}
