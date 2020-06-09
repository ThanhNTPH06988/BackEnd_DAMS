package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_STAFF",schema="FUNDS_BANK")
public class AdminStaffDomain implements Serializable {
	@Id
	@Column(name = "ID", nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_STAFF")
	@SequenceGenerator(name = "SEQ_ADMIN_STAFF", sequenceName = "SEQ_ADMIN_STAFF", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "CODE", nullable = true, length = 50)
	private String code;

	@Column(name = "NAME", nullable = true, length = 100)
	private String name;

	@Column(name = "DATE_OF_BIRTH", nullable = true)
	private Date dateOfBirth;

	@Column(name = "IDCARD", nullable = true, length = 20)
	private String idcard;

	@Column(name = "EMAIL", nullable = true, length = 50)
	private String email;

	@Column(name = "PHONE_NUMBER", nullable = true, length = 20)
	private String phoneNumber;

	@Column(name = "ADDR_DETAIL", nullable = true, length = 100)
	private String addrDetail;

	@Column(name = "ADDR_ID", nullable = true)
	private Long addrId;

	@Column(name = "BRANCH_ID", nullable = true)
	private Long branchId;

	@Column(name = "POSITION_ID", nullable = true)
	private Long positionId;

	@Column(name = "STATUS", nullable = true)
	private Long status;

	@Column(name = "DATE_CREATED", nullable = true)
	private Date dateCreated;

	@Column(name = "USER_ID", nullable = true)
	private Long userId;

	@Column(name = "DELETED", nullable = true, length = 1)
	private String deleted;

	@Column(name = "AVATAR_GUIID", nullable = true)
	private String avatarGuiId;

	@Column(name = "AVATAR_NAME", nullable = true)
	private String avatarName;

	@Column(name = "IS_TRANSACTION", nullable = true)
	private Long isTransaction;

	@OneToOne
	@JoinColumn(name = "BRANCH_ID", insertable = false, updatable = false)
	private AdminBranchDomain branchDomain;

	@OneToOne
	@JoinColumn(name = "POSITION_ID", insertable = false, updatable = false)
	private AdminStaffPositionDomain staffPositionDomain;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ADDR_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private AdminAddressDomain addr;


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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddrDetail() {
		return addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	public Long getAddrId() {
		return addrId;
	}

	public void setAddrId(Long addrId) {
		this.addrId = addrId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
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

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getAvatarGuiId() {
		return avatarGuiId;
	}

	public void setAvatarGuiId(String avatarGuiId) {
		this.avatarGuiId = avatarGuiId;
	}

	public String getAvatarName() {
		return avatarName;
	}

	public void setAvatarName(String avatarName) {
		this.avatarName = avatarName;
	}

	public Long getIsTransaction() {
		return isTransaction;
	}

	public void setIsTransaction(Long isTransaction) {
		this.isTransaction = isTransaction;
	}

	public AdminBranchDomain getBranchDomain() {
		return branchDomain;
	}

	public void setBranchDomain(AdminBranchDomain branchDomain) {
		this.branchDomain = branchDomain;
	}

	public AdminStaffPositionDomain getStaffPositionDomain() {
		return staffPositionDomain;
	}

	public void setStaffPositionDomain(AdminStaffPositionDomain staffPositionDomain) {
		this.staffPositionDomain = staffPositionDomain;
	}

	public AdminAddressDomain getAddr() {
		return addr;
	}

	public void setAddr(AdminAddressDomain addr) {
		this.addr = addr;
	}
}