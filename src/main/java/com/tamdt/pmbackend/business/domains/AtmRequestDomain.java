package com.tamdt.pmbackend.business.domains;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "ATM_REQUEST", schema = "FUNDS_BANK")
public class AtmRequestDomain implements Serializable {
    @Id
    @Column(name = "ID", nullable = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATM_REQUEST")
    @SequenceGenerator(name = "SEQ_ATM_REQUEST", sequenceName = "SEQ_ATM_REQUEST", allocationSize = 1, schema = "FUNDS_BANK")
    private Long id;

    @Column(name = "ATM_CODE", nullable = true, length = 50)
    private String atmCode;

    @Column(name = "ATM_LAT", nullable = true, length = 100)
    private String atmLat;

    @Column(name = "ATM_LONG", nullable = true, length = 100)
    private String atmLong;

    @Column(name = "STATUS", nullable = true)
    private Long status;

    @Column(name = "BRANCH_CODE", nullable = true, length = 50)
    private String branchCode;

    @Column(name = "CODE", nullable = true, length = 50)
    private String code;

    @Column(name = "DATE_REQUEST_FROM", nullable = true)
    private Date dateRequestFrom;

    @Column(name = "DATE_REQUEST_TO", nullable = true)
    private Date dateRequestTo;

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ATM_CODE", referencedColumnName = "CODE", insertable = false, updatable = false)
    @Transient
    private AdminAtmDomain atm;


    @Transient
    private Long countAtmRequest;

    @Transient
    private String branchName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAtmCode() {
        return atmCode;
    }

    public void setAtmCode(String atmCode) {
        this.atmCode = atmCode;
    }

    public String getAtmLat() {
        return atmLat;
    }

    public void setAtmLat(String atmLat) {
        this.atmLat = atmLat;
    }

    public String getAtmLong() {
        return atmLong;
    }

    public void setAtmLong(String atmLong) {
        this.atmLong = atmLong;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDateRequestFrom() {
        return dateRequestFrom;
    }

    public void setDateRequestFrom(Date dateRequestFrom) {
        this.dateRequestFrom = dateRequestFrom;
    }

    public Date getDateRequestTo() {
        return dateRequestTo;
    }

    public void setDateRequestTo(Date dateRequestTo) {
        this.dateRequestTo = dateRequestTo;
    }

    public Long getCountAtmRequest() {
        return countAtmRequest;
    }

    public void setCountAtmRequest(Long countAtmRequest) {
        this.countAtmRequest = countAtmRequest;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public AdminAtmDomain getAtm() {
        return atm;
    }

    public void setAtm(AdminAtmDomain atm) {
        this.atm = atm;
    }
}