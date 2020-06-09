package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminStoragesDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminStoragesCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminStoragesRepository extends BasicRepository<AdminStoragesDomain>, AdminStoragesCustomRepository {
    List<AdminStoragesDomain> findByDeleted(String no);

    List<AdminStoragesDomain> findByDeletedAndStatus(String no, Long active);

    List<AdminStoragesDomain> findByCode(String code);
}