package com.tamdt.pmbackend.business.repositories;

import com.tamdt.pmbackend.base.BasicRepository;
import com.tamdt.pmbackend.business.domains.AdminMenuDomain;
import com.tamdt.pmbackend.business.repositories.custom.AdminMenuCustomRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMenuRepository extends BasicRepository<AdminMenuDomain>, AdminMenuCustomRepository {

    List<AdminMenuDomain> findByParentIdAndMenuLevelAndStatusAndDeletedOrderByOrderIndexAsc(Long parentId,Long menuLevel,Long status,String deleted);

    List<AdminMenuDomain> findByDeletedAndStatusAndMenuLevel(String deleted,Long status,Long level);

}