package com.tamdt.pmbackend.business.domains;

import javax.persistence.*;
import java.util.Date;
import java.io.Serializable;


@Entity
@Table(name = "HIS_ATM_REQUEST", schema = "FUNDS_BANK")
public class HisAtmRequestDomain implements Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_HIS_ATM_REQUEST")
    @SequenceGenerator(name = "SEQ_HIS_ATM_REQUEST", sequenceName = "SEQ_HIS_ATM_REQUEST", allocationSize = 1, schema = "FUNDS_BANK")
    private Long id;

    @Column(name = "STAFF_ID", nullable = true)
    private Long staffId;

    @Column(name = "DATE_HAND", nullable = true)
    private Date dateHand;

    @Column(name = "CONTENT", nullable = true, length = 2000)
    private String content;

    @Column(name = "STATUS", nullable = true)
    private Long status;

    @Column(name = "ATM_REQUEST_ID", nullable = true)
    private Long atmRequestId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STAFF_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private AdminStaffDomain staffE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Date getDateHand() {
        return dateHand;
    }

    public void setDateHand(Date dateHand) {
        this.dateHand = dateHand;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getAtmRequestId() {
        return atmRequestId;
    }

    public void setAtmRequestId(Long atmRequestId) {
        this.atmRequestId = atmRequestId;
    }

	public AdminStaffDomain getStaffE() {
		return staffE;
	}

	public void setStaffE(AdminStaffDomain staffE) {
		this.staffE = staffE;
	}
}