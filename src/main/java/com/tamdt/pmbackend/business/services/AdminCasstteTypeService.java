package com.tamdt.pmbackend.business.services;

import com.tamdt.pmbackend.base.BasicService;
import com.tamdt.pmbackend.business.domains.AdminCasstteTypeDomain;
import com.tamdt.pmbackend.business.repositories.AdminCasstteTypeRepository;

import java.util.List;


public interface AdminCasstteTypeService extends BasicService<AdminCasstteTypeDomain> {
    List<AdminCasstteTypeDomain> getAll();
}