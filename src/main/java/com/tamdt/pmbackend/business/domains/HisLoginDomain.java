package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "HIS_LOGIN",schema="FUNDS_BANK")
public class HisLoginDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HIS_LOGIN")
	@SequenceGenerator(name = "SEQ_HIS_LOGIN", sequenceName = "SEQ_HIS_LOGIN", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "USER_NAME",nullable = true,length = 50)
	private String userName;

	@Column(name = "DATE_LOGIN",nullable = true)
	private Date dateLogin;

	@Column(name = "IP_LOGIN",nullable = true,length = 50)
	private String ipLogin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getDateLogin() {
		return dateLogin;
	}

	public void setDateLogin(Date dateLogin) {
		this.dateLogin = dateLogin;
	}

	public String getIpLogin() {
		return ipLogin;
	}

	public void setIpLogin(String ipLogin) {
		this.ipLogin = ipLogin;
	}

}