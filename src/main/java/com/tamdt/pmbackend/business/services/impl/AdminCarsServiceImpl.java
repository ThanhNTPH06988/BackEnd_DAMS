package com.tamdt.pmbackend.business.services.impl;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.base.BasicServiceImpl;
import com.tamdt.pmbackend.business.domains.AdminUsersDomain;
import com.tamdt.pmbackend.business.dtos.CarsSearchDTO;
import com.tamdt.pmbackend.business.repositories.AdminUsersRepository;
import com.tamdt.pmbackend.common.AppConstant;
import com.tamdt.pmbackend.common.helper.IContext;
import com.tamdt.pmbackend.common.helper.ListJson;
import org.springframework.beans.factory.annotation.Autowired;
import com.tamdt.pmbackend.business.domains.AdminCarsDomain;
import com.tamdt.pmbackend.business.repositories.AdminCarsRepository;
import com.tamdt.pmbackend.business.services.AdminCarsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class AdminCarsServiceImpl extends BasicServiceImpl<AdminCarsDomain> implements AdminCarsService {

	@Autowired
	private AdminCarsRepository repo;

	@Override
	public BasicRepository<AdminCarsDomain> getRepository() {
		return repo;
	}

	@Autowired
	private AdminUsersRepository userRepo;

	@Override
	public ListJson<AdminCarsDomain> searchCars(CarsSearchDTO searchDTO) {

		List<AdminCarsDomain> lstRs = new ArrayList<>();
		Long countSearch = repo.countSearchCars(searchDTO);

		if (countSearch > 0) {
			lstRs = repo.searchCars(searchDTO);
		}
		return new ListJson<AdminCarsDomain>(lstRs, (long) countSearch);
	}

	@Override
	public void deletedCars(Long id) {
		repo.deletedCars(id);
	}

	@Override
	@Transactional
	public AdminCarsDomain saveOrUpdate(AdminCarsDomain adminCarsDomain) {

		//staff old
		AdminCarsDomain adminCarsOld = null;
		boolean isExitCheck = false;

		if(adminCarsDomain.getId() == null) {
			adminCarsOld = repo.getOne(adminCarsDomain.getId());

			isExitCheck = adminCarsOld != null && !adminCarsOld.getCode().toLowerCase().trim().equals(adminCarsDomain.getCode().toLowerCase().trim());
		}

		if(adminCarsDomain.getId() == null || isExitCheck) {
			Long isExitCode = repo.isExitCode(adminCarsDomain.getCode());
			if(isExitCode > AppConstant.ZERO)
				return null;
		}

		AdminUsersDomain userLogin = userRepo.findByUserNameAndDeleted(IContext.getUserNameLogin(), AppConstant.DELETED.NO);

		if(adminCarsDomain.getId() == null) {

			/*add new*/
			adminCarsDomain.setDateCreated(new Date());
			adminCarsDomain.setStatus(AppConstant.STATUS.ACTIVE);
			adminCarsDomain.setDeleted(AppConstant.DELETED.NO);
			adminCarsDomain.setUserId(userLogin.getId());
		} else {
			/*edit*/
			adminCarsDomain.setDateCreated(adminCarsOld.getDateCreated());
			adminCarsDomain.setStatus(adminCarsOld.getStatus());
			adminCarsDomain.setDeleted(adminCarsOld.getDeleted());
			adminCarsDomain.setUserId(adminCarsOld.getUserId());
		}

		super.saveOrUpdate(adminCarsDomain);

		return adminCarsDomain;
	}
}