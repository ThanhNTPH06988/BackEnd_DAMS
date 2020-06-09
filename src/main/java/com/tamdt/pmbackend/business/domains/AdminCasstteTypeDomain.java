package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_CASSTTE_TYPE",schema="FUNDS_BANK")
public class AdminCasstteTypeDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_CASSTTE_TYPE")
	@SequenceGenerator(name = "SEQ_ADMIN_CASSTTE_TYPE", sequenceName = "SEQ_ADMIN_CASSTTE_TYPE", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "CODE",nullable = true,length = 50)
	private String code;

	@Column(name = "NAME",nullable = true,length = 100)
	private String name;

	@Column(name = "DATE_CREATED",nullable = true)
	private Date dateCreated;

	@Column(name = "STATUS",nullable = true)
	private Long status;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "LAT_UPDATE",nullable = true)
	private Date latUpdate;

	@Column(name = "USER_UPDATE_ID",nullable = true)
	private Long userUpdateId;

	@Column(name = "DELETED",nullable = true,length = 1)
	private String deleted;

	@Column(name = "VALUE",nullable = true,length = 50)
	private String value;

	@Column(name = "CASSTTE_SIZE",nullable = true,length = 50)
	private String casstteSize;

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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getLatUpdate() {
		return latUpdate;
	}

	public void setLatUpdate(Date latUpdate) {
		this.latUpdate = latUpdate;
	}

	public Long getUserUpdateId() {
		return userUpdateId;
	}

	public void setUserUpdateId(Long userUpdateId) {
		this.userUpdateId = userUpdateId;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getCasstteSize() {
		return casstteSize;
	}

	public void setCasstteSize(String casstteSize) {
		this.casstteSize = casstteSize;
	}

}