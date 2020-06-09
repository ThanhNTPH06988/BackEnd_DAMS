package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.CasstteSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminUsersRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.IContext;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import com.tamdt.pmbackend.business.domains.AdminCasstteDomain;
import com.tamdt.pmbackend.business.repositories.AdminCasstteRepository;
import com.tamdt.pmbackend.business.services.AdminCasstteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdminCasstteServiceImpl extends BasicServiceImpl<AdminCasstteDomain> implements AdminCasstteService {

	@Autowired
	private AdminCasstteRepository repo;

	@Autowired
	private AdminUsersRepository repoUser;

	@Override
	public BasicRepository<AdminCasstteDomain> getRepository() {
		return repo;
	}

	@Override
	public AdminCasstteDomain saveOrUpdate(AdminCasstteDomain adminCasstteDomain) {
		AdminUsersDomain userLogin = repoUser.findByUserNameAndDeleted(IContext.getUserNameLogin(), AppConstant.DELETED.NO);
		Long cout = repo.exitCasstteCode(adminCasstteDomain.getCode());
		if(adminCasstteDomain.getId() == null) {
			if(cout > 0L){
				return new AdminCasstteDomain();
			}
			adminCasstteDomain.setStatus(AppConstant.STATUS.ACTIVE);
			adminCasstteDomain.setDateCreated(new Date());
			adminCasstteDomain.setUserId(userLogin.getId());
			adminCasstteDomain.setDeleted(AppConstant.DELETED.NO);
		}
		else {
			adminCasstteDomain.setLatUpdate(new Date());
			adminCasstteDomain.setUserUpdateId(userLogin.getId());
		}
		repo.save(adminCasstteDomain);
		return adminCasstteDomain;
	}

	@Override
	public void deletedCasstte(Long atmId) {
		repo.deletedCasstte(atmId);
	}

	@Override
	public ListJson<AdminCasstteDomain> searchCasstte(CasstteSearchDTO searchDTO) {
		List<AdminCasstteDomain> lstRs = new ArrayList<>();
		Long countSearch = repo.countSearchCasstte(searchDTO);

		if (countSearch > 0) {
			lstRs = repo.searchCasstte(searchDTO);
		}
		return new ListJson<AdminCasstteDomain>(lstRs, (long) countSearch);
	}

}