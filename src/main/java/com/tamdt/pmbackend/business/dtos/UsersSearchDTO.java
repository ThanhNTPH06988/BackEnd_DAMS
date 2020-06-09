package com.tamdt.pmbackend.business.dtos;

import java.util.Date;

public class UsersSearchDTO {

    private String userName;
    private Date dateFrom;
    private Date dateTo;
    private String staffName;
    private Long staffPositionId;
    private Long status;
    private int page;
    private int size;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateFrom()
    {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getStaffPositionId() {
        return staffPositionId;
    }

    public void setStaffPositionId(Long staffPositionId) {
        this.staffPositionId = staffPositionId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
