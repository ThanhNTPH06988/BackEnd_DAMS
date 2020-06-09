package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminAtmDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminAtmCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminAtmRepository extends BasicRepository<AdminAtmDomain>, AdminAtmCustomRepository {

    List<AdminAtmDomain> findByBranchIdAndStatusAndDeleted(Long id, Long active, String no); //huongmk dung phan nay


}