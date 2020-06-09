package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_USER_ROLE",schema="FUNDS_BANK")
public class AdminUserRoleDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_USER_ROLE")
	@SequenceGenerator(name = "SEQ_ADMIN_USER_ROLE", sequenceName = "SEQ_ADMIN_USER_ROLE", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "ROLE_ID",nullable = true)
	private Long roleId;

	@Column(name = "DATE_CREATED",nullable = true)
	private Date dateCreated;

	@Column(name = "CREATE_BY",nullable = true)
	private Long createBy;

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

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

}