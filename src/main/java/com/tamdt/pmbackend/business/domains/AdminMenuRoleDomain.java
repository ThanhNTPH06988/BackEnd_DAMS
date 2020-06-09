package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_MENU_ROLE",schema="FUNDS_BANK")
public class AdminMenuRoleDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_MENU_ROLE")
	@SequenceGenerator(name = "SEQ_ADMIN_MENU_ROLE", sequenceName = "SEQ_ADMIN_MENU_ROLE", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "MENU_ID",nullable = true)
	private Long menuId;

	@Column(name = "ROLE_ID",nullable = true)
	private Long roleId;

	@Column(name = "ROLE_CODE",nullable = true,length = 200)
	private String roleCode;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "DATE_CREATED",nullable = true)
	private Date dateCreated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}