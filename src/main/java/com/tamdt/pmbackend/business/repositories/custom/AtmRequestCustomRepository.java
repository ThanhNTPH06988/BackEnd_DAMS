package com.tamdt.pmbackend.business.repositories.custom;


import com.tamdt.pmbackend.business.domains.AtmRequestDomain;
import com.tamdt.pmbackend.business.dtos.RequestSearchDTO;

import java.util.List;

public interface AtmRequestCustomRepository {
    List<AtmRequestDomain> searchData(RequestSearchDTO searchDTO);

    Long countSearchData(RequestSearchDTO searchDTO);
}