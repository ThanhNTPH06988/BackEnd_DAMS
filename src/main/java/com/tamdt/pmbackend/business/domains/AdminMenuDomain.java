package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;
import java.util.List;


@Entity
@SqlResultSetMapping(
	name = "MenuMapping",
	entities = @EntityResult(
		entityClass = AdminMenuDomain.class,
		fields = {
			@FieldResult(name = "id", column = "ID"),
			@FieldResult(name = "code", column = "CODE"),
			@FieldResult(name = "name", column = "NAME"),
			@FieldResult(name = "icon", column = "ICON"),
			@FieldResult(name = "urlPath", column = "URL_PATH"),
			@FieldResult(name = "parentId", column = "PARENT_ID"),
			@FieldResult(name = "menuLevel", column = "MENU_LEVEL"),
			@FieldResult(name = "status", column = "STATUS"),
			@FieldResult(name = "deleted", column = "DELETED"),
			@FieldResult(name = "dateCreated", column = "DATE_CREATED"),
			@FieldResult(name = "userId", column = "USER_ID"),
			@FieldResult(name = "isSubmenu", column = "IS_SUBMENU"),
			@FieldResult(name = "orderIndex", column = "ORDER_INDEX")
		}
	)
)
@Table(name = "ADMIN_MENU",schema="FUNDS_BANK")
public class AdminMenuDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_MENU")
	@SequenceGenerator(name = "SEQ_ADMIN_MENU", sequenceName = "SEQ_ADMIN_MENU", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "CODE",nullable = true,length = 100)
	private String code;

	@Column(name = "NAME",nullable = true,length = 200)
	private String name;

	@Column(name = "ICON",nullable = true,length = 500)
	private String icon;

	@Column(name = "URL_PATH",nullable = true,length = 200)
	private String urlPath;

	@Column(name = "PARENT_ID",nullable = true)
	private Long parentId;

	@Column(name = "MENU_LEVEL",nullable = true)
	private Long menuLevel;

	@Column(name = "STATUS",nullable = true)
	private Long status;

	@Column(name = "DELETED",nullable = true,length = 1)
	private String deleted;

	@Column(name = "DATE_CREATED",nullable = true)
	private Date dateCreated;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "IS_SUBMENU",nullable = true)
	private Long isSubmenu;

	@Column(name = "ORDER_INDEX",nullable = true)
	private Long orderIndex;

	@Transient
	private List<AdminMenuDomain> lstSubmenu;

	@Transient
	private List<AdminRolesDomain> lstRoles;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Long menuLevel) {
		this.menuLevel = menuLevel;
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

	public Long getIsSubmenu() {
		return isSubmenu;
	}

	public void setIsSubmenu(Long isSubmenu) {
		this.isSubmenu = isSubmenu;
	}

	public List<AdminMenuDomain> getLstSubmenu() {
		return lstSubmenu;
	}

	public void setLstSubmenu(List<AdminMenuDomain> lstSubmenu) {
		this.lstSubmenu = lstSubmenu;
	}

	public Long getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(Long orderIndex) {
		this.orderIndex = orderIndex;
	}

	public List<AdminRolesDomain> getLstRoles() {
		return lstRoles;
	}

	public void setLstRoles(List<AdminRolesDomain> lstRoles) {
		this.lstRoles = lstRoles;
	}
}