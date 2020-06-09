package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_CARS",schema="FUNDS_BANK")
public class AdminCarsDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_CARS")
	@SequenceGenerator(name = "SEQ_ADMIN_CARS", sequenceName = "SEQ_ADMIN_CARS", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "CODE",nullable = true,length = 50)
	private String code;

	@Column(name = "NAME",nullable = true,length = 100)
	private String name;

	@Column(name = "LICENSE_NUMBER",nullable = true,length = 50)
	private String licenseNumber;

	@Column(name = "BRANCH_ID",nullable = true)
	private Long branchId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private AdminBranchDomain adminBranch;

	@Column(name = "TICKET_ID",nullable = true)
	private Long ticketId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "TICKET_ID", referencedColumnName = "ID", insertable = false, updatable = false)
	private AdminCarsTicketDomain carsTicket;

	@Column(name = "CAR_TYPE_ID",nullable = true)
	private Long carTypeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CAR_TYPE_ID", referencedColumnName = "ID",insertable = false, updatable = false)
    private AdminCarsTypeDomain carsType;

	@Column(name = "COLOR",nullable = true,length = 50)
	private String color;

	@Column(name = "YEAR_OF_MANUFACTURE",nullable = true)
	private Long yearOfManufacture;

	@Column(name = "COUNTRY_OF_MANUFACTURE",nullable = true,length = 50)
	private String countryOfManufacture;

	@Column(name = "REGISTRY_DATE",nullable = true)
    @Temporal(TemporalType.DATE)
	private Date registryDate;

	@Column(name = "REGISTRY_DATE_VALID",nullable = true)
    @Temporal(TemporalType.DATE)
	private Date registryDateValid;

	@Column(name = "FIRST_REGIS_DATE",nullable = true)
    @Temporal(TemporalType.DATE)
	private Date firstRegisDate;

	@Column(name = "LIFETIME_LIMIT",nullable = true)
	private Long lifetimeLimit;

	@Column(name = "WEIGHT_TOTAL",nullable = true)
	private Long weightTotal;

	@Column(name = "WEIGHT_GOODS",nullable = true)
	private Long weightGoods;

	@Column(name = "STATUS",nullable = true)
	private Long status;

	@Column(name = "DATE_CREATED",nullable = true)
    @Temporal(TemporalType.DATE)
	private Date dateCreated;

	@Column(name = "USER_ID",nullable = true)
	private Long userId;

	@Column(name = "DELETED",nullable = true,length = 1)
	private String deleted;

	@Column(name = "LAST_UPDATE",nullable = true)
    @Temporal(TemporalType.DATE)
	private Date lastUpdate;

	@Column(name = "USER_ID_UPDATE",nullable = true)
	private Long userIdUpdate;

	@Column(name = "AVATAR_GUIID", nullable = true)
	private String avatarGuiId;

	@Column(name = "AVATAR_NAME", nullable = true)
	private String avatarName;

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

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Long carTypeId) {
		this.carTypeId = carTypeId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Long getYearOfManufacture() {
		return yearOfManufacture;
	}

	public void setYearOfManufacture(Long yearOfManufacture) {
		this.yearOfManufacture = yearOfManufacture;
	}

	public String getCountryOfManufacture() {
		return countryOfManufacture;
	}

	public void setCountryOfManufacture(String countryOfManufacture) {
		this.countryOfManufacture = countryOfManufacture;
	}

	public Date getRegistryDate() {
		return registryDate;
	}

	public void setRegistryDate(Date registryDate) {
		this.registryDate = registryDate;
	}

	public Date getRegistryDateValid() {
		return registryDateValid;
	}

	public void setRegistryDateValid(Date registryDateValid) {
		this.registryDateValid = registryDateValid;
	}

	public Date getFirstRegisDate() {
		return firstRegisDate;
	}

	public void setFirstRegisDate(Date firstRegisDate) {
		this.firstRegisDate = firstRegisDate;
	}

	public Long getLifetimeLimit() {
		return lifetimeLimit;
	}

	public void setLifetimeLimit(Long lifetimeLimit) {
		this.lifetimeLimit = lifetimeLimit;
	}

	public Long getWeightTotal() {
		return weightTotal;
	}

	public void setWeightTotal(Long weightTotal) {
		this.weightTotal = weightTotal;
	}

	public Long getWeightGoods() {
		return weightGoods;
	}

	public void setWeightGoods(Long weightGoods) {
		this.weightGoods = weightGoods;
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

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Long getUserIdUpdate() {
		return userIdUpdate;
	}

	public void setUserIdUpdate(Long userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

    public AdminCarsTypeDomain getCarsType() {
        return carsType;
    }

    public void setCarsType(AdminCarsTypeDomain carsType) {
        this.carsType = carsType;
    }

	public AdminBranchDomain getAdminBranch() {
		return adminBranch;
	}

	public void setAdminBranch(AdminBranchDomain adminBranch) {
		this.adminBranch = adminBranch;
	}

	public AdminCarsTicketDomain getCarsTicket() {
		return carsTicket;
	}

	public void setCarsTicket(AdminCarsTicketDomain carsTicket) {
		this.carsTicket = carsTicket;
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
}