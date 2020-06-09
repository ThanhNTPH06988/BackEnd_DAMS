package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminBranchDomain;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.AtmSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminBranchRepository;
import com.tamdt.pmbackend.business.repositories.AdminUsersRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.IContext;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import com.tamdt.pmbackend.business.domains.AdminAtmDomain;
import com.tamdt.pmbackend.business.repositories.AdminAtmRepository;
import com.tamdt.pmbackend.business.services.AdminAtmService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdminAtmServiceImpl extends BasicServiceImpl<AdminAtmDomain> implements AdminAtmService {

	@Autowired
	private AdminAtmRepository repo;

	@Autowired
	private AdminUsersRepository repoUser;

	@Autowired
	private AdminBranchRepository repoBranch;

	@Override
	public BasicRepository<AdminAtmDomain> getRepository() {
		return repo;
	}

	@Override
	public AdminAtmDomain saveOrUpdate(AdminAtmDomain adminAtmDomain) {
		AdminUsersDomain userLogin = repoUser.findByUserNameAndDeleted(IContext.getUserNameLogin(), AppConstant.DELETED.NO);
		Long cout = repo.exitAtmCode(adminAtmDomain.getCode());
		if(adminAtmDomain.getId() == null) {
			if(cout > 0L){
				return new AdminAtmDomain();
			}
			adminAtmDomain.setStatus(AppConstant.STATUS.ACTIVE);
			adminAtmDomain.setDateCreated(new Date());
			adminAtmDomain.setUserId(userLogin.getId());
			adminAtmDomain.setDeleted(AppConstant.DELETED.NO);
		}
		repo.save(adminAtmDomain);
		return adminAtmDomain;
	}

	@Override
	public void deletedAtm(Long atmId) {
		repo.deletedAtm(atmId);
	}

	@Override
	public ListJson<AdminAtmDomain> searchAtms(AtmSearchDTO searchDTO) {
		List<AdminAtmDomain> lstRs = new ArrayList<>();
		Long countSearch = repo.countSearchAtms(searchDTO);

		if (countSearch > 0) {
			lstRs = repo.searchAtms(searchDTO);
		}
		return new ListJson<AdminAtmDomain>(lstRs, (long) countSearch);
	}

	@Override
	@Transactional
	public void lockOrUnlock(Long atmId, Long type) {
		repo.lockOrUnlock(atmId,type);
	}

	//huongmk dung phan nay
	@Override
	public List<AdminAtmDomain> getAtmByBranchCode(String branchCode) {
		AdminBranchDomain branch = repoBranch.findByCode(branchCode);
		List<AdminAtmDomain> lstAtm = repo.findByBranchIdAndStatusAndDeleted(branch.getId(), AppConstant.STATUS.ACTIVE, AppConstant.DELETED.NO);

		return lstAtm;
	}
}