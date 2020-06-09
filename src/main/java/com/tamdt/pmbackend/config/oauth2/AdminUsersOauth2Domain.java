package com.tamdt.pmbackend.config.oauth2;

import com.tamdt.pmbackend.business.domains.AdminRolesDomain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "ADMIN_USERS", schema = "FUNDS_BANK")
public class AdminUsersOauth2Domain implements Serializable {
    @Id
    @Column(name = "ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ADMIN_USERS")
    @SequenceGenerator(name = "SEQ_ADMIN_USERS", sequenceName = "SEQ_ADMIN_USERS", allocationSize = 1, schema = "FUNDS_BANK")
    private Long id;

    @Column(name = "USER_NAME", nullable = true, length = 50)
    private String userName;

    @Column(name = "PASSWORD", nullable = true, length = 200)
    private String password;

    @Column(name = "IS_ACTIVE", nullable = true)
    private Long isActive;

    @Column(name = "FIRST_PASS_CHANGE", nullable = true, length = 1)
    private String firstPassChange;

    @Column(name = "USER_ID", nullable = true)
    private Long userId;

    @Column(name = "DATE_CREATED", nullable = true)
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

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ADMIN_USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    )
    private Set<AdminRolesDomain> lstRoles;

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
}