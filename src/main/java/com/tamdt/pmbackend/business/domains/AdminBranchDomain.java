package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ADMIN_BRANCH", schema = "FUNDS_BANK")
public class AdminBranchDomain implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_BRANCH")
    @SequenceGenerator(name = "SEQ_ADMIN_BRANCH", sequenceName = "SEQ_ADMIN_BRANCH", allocationSize = 1, schema = "FUNDS_BANK")
    private Long id;

    @Column(name = "CODE", nullable = true, length = 50)
    private String code;

    @Column(name = "NAME", nullable = true, length = 200)
    private String name;

    @Column(name = "ADDR_DETAIL", nullable = true, length = 100)
    private String addrDetail;

    @Column(name = "ADDR_ID", nullable = true)
    private Long addrId;

    @Column(name = "STAFF_ID", nullable = true)
    private Long staffId;

    @Column(name = "STATUS", nullable = true)
    private Long status;

    @Column(name = "USER_ID", nullable = true)
    private Long userId;

    @Column(name = "DATE_CREATED", nullable = true)
    private Date dateCreated;

    @Column(name = "DELETED", nullable = true, length = 1)
    private String deleted;

    @Column(name = "LAT_UPDATE", nullable = true)
    private Date latUpdate;

    @Column(name = "USER_UPDATE_ID", nullable = true)
    private Long userUpdateId;

    @Column(name = "RANK", nullable = true)
    private Long rank;

    @Column(name = "PARENT_ID", nullable = true, length = 20)
    private String parentId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADDR_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private AdminAddressDomain addr;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private AdminUsersDomain user;

    @Transient
    private String managerName;

    @Transient
    private List<AdminStaffDomain> lstStaff;

    @Transient
    private String staffCode;

    public AdminUsersDomain getUser() {
        return user;
    }

    public void setUser(AdminUsersDomain user) {
        this.user = user;
    }

    public AdminAddressDomain getAddr() {
        return addr;
    }

    public void setAddr(AdminAddressDomain addr) {
        this.addr = addr;
    }

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

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
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

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public List<AdminStaffDomain> getLstStaff() {
        return lstStaff;
    }

    public void setLstStaff(List<AdminStaffDomain> lstStaff) {
        this.lstStaff = lstStaff;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }
}