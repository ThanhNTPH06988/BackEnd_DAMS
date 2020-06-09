package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_ADDRESS",schema="FUNDS_BANK")
public class AdminAddressDomain implements Serializable {
	@Id
	@Column(name = "ID",nullable = true)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_ADDRESS")
	@SequenceGenerator(name = "SEQ_ADMIN_ADDRESS", sequenceName = "SEQ_ADMIN_ADDRESS", allocationSize = 1, schema = "FUNDS_BANK")
	private Long id;

	@Column(name = "PROVINCE_CODE",nullable = true,length = 50)
	private String provinceCode;

	@Column(name = "DISTRICT_CODE",nullable = true,length = 100)
	private String districtCode;

	@Column(name = "WARD_CODE",nullable = true,length = 200)
	private String wardCode;

	@Column(name = "PROVINCE_NAME",nullable = true,length = 300)
	private String provinceName;

	@Column(name = "DISTRICT_NAME",nullable = true,length = 500)
	private String districtName;

	@Column(name = "WARD_NAME",nullable = true,length = 1000)
	private String wardName;

	@Column(name = "LEVEL_CODE",nullable = true,length = 20)
	private String levelCode;

	@Column(name = "LEVEL_NAME",nullable = true,length = 50)
	private String levelName;

	@Column(name = "DATE_CREATED",nullable = true)
	private Date dateCreated;

	@Column(name = "FULL_NAME",nullable = true,length = 2000)
	private String fullName;

	@Column(name = "RANK",nullable = true)
	private Long rank;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getWardName() {
		return wardName;
	}

	public void setWardName(String wardName) {
		this.wardName = wardName;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

}