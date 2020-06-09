package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@SqlResultSetMapping(
	name = "RolesMapping",
	entities = @EntityResult(
		entityClass = AdminRolesDomain.class,
		fields = {
			@FieldResult(name = "id", column = "ID"),
			@FieldResult(name = "code", column = "CODE"),
			@FieldResult(name = "name", column = "NAME"),
			@FieldResult(name = "userId", column = "USER_ID"),
			@FieldResult(name = "dateCreated", column = "DATE_CREATED"),
			@FieldResult(name = "status", column = "STATUS"),
			@FieldResult(name = "deleted", column = "DELETED"),
			@FieldResult(name = "lastUpdate", column = "LAST_UPDATE")
		}
	)
)
@Table(name = "ADMIN_ROLES",schema="FUNDS_BANK")
public class AdminRolesDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_ROLES")
	@SequenceGenerator(name = "SEQ_ADMIN_ROLES", sequenceName = "SEQ_ADMIN_ROLES", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "CODE",nullable = true,length = 200)
	private String code;

	@Column(name = "NAME",nullable = true,length = 1000)
	private String name;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "DATE_CREATED",nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateCreated;

	@Column(name = "STATUS",nullable = true)
	private Long status;

	@Column(name = "DELETED",nullable = true,length = 1)
	private String deleted;

	@Column(name = "LAST_UPDATE",nullable = true)
	private Date lastUpdate;

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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}