package com.tamdt.pmbackend.business.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "ADMIN_USERS", schema = "FUNDS_BANK")
public class AdminUsersDomain implements Serializable {
    @Id
    @Column(name = "ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_USERS")
    @SequenceGenerator(name = "SEQ_ADMIN_USERS", sequenceName = "SEQ_ADMIN_USERS", allocationSize = 1, schema = "FUNDS_BANK")
    private Long id;

    @Column(name = "USER_NAME", nullable = true, length = 50)
    private String userName;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = true, length = 200)
    private String password;

    @Column(name = "IS_ACTIVE", nullable = true)
    private Long isActive;

    @Column(name = "FIRST_PASS_CHANGE", nullable = true, length = 1)
    private String firstPassChange;

    @Column(name = "USER_ID", nullable = true)
    private Long userId;

    @Column(name = "DATE_CREATED", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    @Column(name = "LAST_UPDATE", nullable = true)
    private Date lastUpdate;

    @Column(name = "DELETED", nullable = true, length = 1)
    private String deleted;

    @Column(name = "STATUS", nullable = true)
    private Long status;

    @Column(name = "LAST_LOGIN", nullable = true)
    private Date lastLogin;

    @Column(name = "NUMBER_LOGIN", nullable = true)
    private Long numberLogin;

    @Column(name = "USER_NAME_CREATE", nullable = true)
    private String userNameCreate;

    @Column(name = "PASS_TRANS", nullable = true)
    private String passTrans;

    @Column(name = "STAFF_ID", nullable = true)
    private Long staffId;

    @Column(name = "AVATAR_GUIID", nullable = true)
    private String avatarGuiId;

    @Column(name = "AVATAR_NAME", nullable = true)
    private String avatarName;

    @Column(name = "IS_ROOT", nullable = true)
    private Long isRoot;

    @Transient
    private Set<AdminRolesDomain> lstRoles;

    @OneToOne
    @JoinColumn(name = "STAFF_ID",insertable = false, updatable = false)
    private AdminStaffDomain staffDomain;

    @Transient
    private AdminStaffDomain staffDomainAdd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIsActive() {
        return isActive;
    }

    public void setIsActive(Long isActive) {
        this.isActive = isActive;
    }

    public String getFirstPassChange() {
        return firstPassChange;
    }

    public void setFirstPassChange(String firstPassChange) {
        this.firstPassChange = firstPassChange;
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

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getNumberLogin() {
        return numberLogin;
    }

    public void setNumberLogin(Long numberLogin) {
        this.numberLogin = numberLogin;
    }

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    public Set<AdminRolesDomain> getLstRoles() {
        return lstRoles;
    }

    public void setLstRoles(Set<AdminRolesDomain> lstRoles) {
        this.lstRoles = lstRoles;
    }

    public String getPassTrans() {
        return passTrans;
    }

    public void setPassTrans(String passTrans) {
        this.passTrans = passTrans;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getIsRoot() {
        return isRoot;
    }

    public void setIsRoot(Long isRoot) {
        this.isRoot = isRoot;
    }

    public AdminStaffDomain getStaffDomain() {
        return staffDomain;
    }

    public void setStaffDomain(AdminStaffDomain staffDomain) {
        this.staffDomain = staffDomain;
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

    public AdminStaffDomain getStaffDomainAdd() {
        return staffDomainAdd;
    }

    public void setStaffDomainAdd(AdminStaffDomain staffDomainAdd) {
        this.staffDomainAdd = staffDomainAdd;
    }
}