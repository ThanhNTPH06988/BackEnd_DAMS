package com.tamdt.pmbackend.business.dtos;

import java.util.Date;

public class MenusSearchDTO {
    private String code;
    private String name;
    private Long menuLevel;
    private Long status;
    private Date dateCreatedTo;
    private Date dateCreatedFrom;
    private int page;
    private int size;

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

    public Long getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(Long menuLevel) {
        this.menuLevel = menuLevel;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getDateCreatedTo() {
        return dateCreatedTo;
    }

    public void setDateCreatedTo(Date dateCreatedTo) {
        this.dateCreatedTo = dateCreatedTo;
    }

    public Date getDateCreatedFrom() {
        return dateCreatedFrom;
    }

    public void setDateCreatedFrom(Date dateCreatedFrom) {
        this.dateCreatedFrom = dateCreatedFrom;
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
