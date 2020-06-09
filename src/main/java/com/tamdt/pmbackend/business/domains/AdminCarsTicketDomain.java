package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_CARS_TICKET",schema="FUNDS_BANK")
public class AdminCarsTicketDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_CARS_TICKET")
	@SequenceGenerator(name = "SEQ_ADMIN_CARS_TICKET", sequenceName = "SEQ_ADMIN_CARS_TICKET", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "CODE",nullable = true,length = 50)
	private String code;

	@Column(name = "NAME",nullable = true,length = 100)
	private String name;

	@Column(name = "DATE_CREATED",nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateCreated;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "LAST_UPDATE",nullable = true)
	private Date lastUpdate;

	@Column(name = "USER_UPDATE_ID",nullable = true)
	private Long userUpdateId;

	@Column(name = "STATUS",nullable = true)
	private Long status;

	@Column(name = "DELETED",nullable = true,length = 1)
	private String deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getUserUpdateId() {
		return userUpdateId;
	}

	public void setUserUpdateId(Long userUpdateId) {
		this.userUpdateId = userUpdateId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}