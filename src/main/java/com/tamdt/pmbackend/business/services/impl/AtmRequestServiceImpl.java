package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminAtmDomain;
import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.domains.AtmRequestDomain;
import com.tamdt.pmbackend.business.dtos.RequestSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminAtmRepository;
import com.tamdt.pmbackend.business.repositories.AdminBranchRepository;
import com.tamdt.pmbackend.business.repositories.AtmRequestRepository;
import com.tamdt.pmbackend.business.services.AtmRequestService;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AtmRequestServiceImpl extends BasicServiceImpl<AtmRequestDomain> implements AtmRequestService {

	@Autowired
	private AtmRequestRepository repo;

	@Autowired
	private AdminAtmRepository repoAtm;

	@Autowired
	private AdminBranchRepository repoBranch;

	@Override
	public BasicRepository<AtmRequestDomain> getRepository() {
		return repo;
	}

	@Override
	public ListJson<AtmRequestDomain> searchData(RequestSearchDTO dtoSearch) {
		List<AtmRequestDomain> lstRequest = new ArrayList<>();
		Long count = repo.countSearchData(dtoSearch);
		if (count > 0) {
			lstRequest = repo.searchData(dtoSearch);

			for (AtmRequestDomain req: lstRequest) {
				AdminBranchDomain branch = repoBranch.findByCode(req.getBranchCode());
				if (branch!=null) {
					List<AdminAtmDomain> lstAtm = repoAtm.findByBranchIdAndStatusAndDeleted(branch.getId(), AppConstant.STATUS.ACTIVE, AppConstant.DELETED.NO);
					req.setCountAtmRequest(lstAtm != null ? (long) lstAtm.size() : 0L);
					req.setBranchName(branch.getName());
				}
			}
		}
		return new ListJson<AtmRequestDomain>(lstRequest, count);
	}
}