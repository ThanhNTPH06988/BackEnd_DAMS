package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_CASSTTE",schema="FUNDS_BANK")
public class AdminCasstteDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_CASSTTE")
	@SequenceGenerator(name = "SEQ_ADMIN_CASSTTE", sequenceName = "SEQ_ADMIN_CASSTTE", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "CODE",nullable = true,length = 8)
	private String code;

	@Column(name = "CASSTTE_TYPE_ID",nullable = true)
	private Long casstteTypeId;

	@Column(name = "STORAGE_ID",nullable = true)
	private Long storageId;

	@Column(name = "BRANCH_ID",nullable = true)
	private Long branchId;

	@Column(name = "STATUS",nullable = true)
	private Long status;

	@Column(name = "DATE_CREATED",nullable = true)
	private Date dateCreated;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;


	@Column(name = "USER_UPDATE_ID",nullable = true)
	private Long userUpdateId;

	@Column(name = "MAX_BILL",nullable = true)
	private Long maxBill;

	@Column(name = "VALUE",nullable = true,length = 50)
	private String value;

	@Column(name = "LAST_UPDATE",nullable = true)
	private Date lastUpdate;

	@Column(name = "DELETED",nullable = true,length = 1)
	private String deleted;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private AdminBranchDomain branch;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CASSTTE_TYPE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private AdminCasstteTypeDomain type;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STORAGE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private AdminStoragesDomain storage;

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

	public Long getCasstteTypeId() {
		return casstteTypeId;
	}

	public void setCasstteTypeId(Long casstteTypeId) {
		this.casstteTypeId = casstteTypeId;
	}

	public Long getStorageId() {
		return storageId;
	}

	public void setStorageId(Long storageId) {
		this.storageId = storageId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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

	public Long getMaxBill() {
		return maxBill;
	}

	public void setMaxBill(Long maxBill) {
		this.maxBill = maxBill;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getLatUpdate() {
		return lastUpdate;
	}

	public void setLatUpdate(Date latUpdate) {
		this.lastUpdate = latUpdate;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public AdminBranchDomain getBranch() {
		return branch;
	}

	public void setBranch(AdminBranchDomain branch) {
		this.branch = branch;
	}

	public AdminCasstteTypeDomain getType() {
		return type;
	}

	public void setType(AdminCasstteTypeDomain type) {
		this.type = type;
	}

	public AdminStoragesDomain getStorage() {
		return storage;
	}

	public void setStorage(AdminStoragesDomain storage) {
		this.storage = storage;
	}
}