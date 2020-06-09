package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ADMIN_ATM", schema = "FUNDS_BANK")
public class AdminAtmDomain implements Serializable {
    @Id
    @Column(name = "ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_ATM")
    @SequenceGenerator(name = "SEQ_ADMIN_ATM", sequenceName = "SEQ_ADMIN_ATM", allocationSize = 1, schema = "FUNDS_BANK")
    private Long id;

    @Column(name = "CODE", nullable = true, length = 50)
    private String code;

    @Column(name = "LATITUDE", nullable = true, length = 100)
    private String latitude;

    @Column(name = "LONGITUDE", nullable = true, length = 100)
    private String longitude;

    @Column(name = "ADDR_DETAIL", nullable = true, length = 100)
    private String addrDetail;

    @Column(name = "ADDR_ID", nullable = true)
    private Long addrId;

    @Column(name = "STATUS", nullable = true)
    private Long status;

    @Column(name = "DATE_CREATED", nullable = true)
    private Date dateCreated;

    @Column(name = "USER_ID", nullable = true)
    private Long userId;

    @Column(name = "DELETED", nullable = true, length = 1)
    private String deleted;

    @Column(name = "BRANCH_ID", nullable = true)
    private Long branchId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDR_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private AdminAddressDomain addr;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private AdminBranchDomain branch;

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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
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

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public AdminAddressDomain getAddr() {
        return addr;
    }

    public void setAddr(AdminAddressDomain addr) {
        this.addr = addr;
    }

    public AdminBranchDomain getBranch() {
        return branch;
    }

    public void setBranch(AdminBranchDomain branch) {
        this.branch = branch;
    }

}