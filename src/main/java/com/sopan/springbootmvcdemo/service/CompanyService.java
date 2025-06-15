package com.sopan.springbootmvcdemo.service;

import com.sopan.springbootmvcdemo.dto.CompanyDTO;
import com.sopan.springbootmvcdemo.exception.CompanyNoSuchElementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing Company data.
 * Defines core business operations for companies.
 */
public interface CompanyService {

    Page<CompanyDTO> findPaginatedCompanies(int pageNum, int pageSize);

    Page<CompanyDTO> findPaginatedCompaniesByName(String name, Pageable pageable);

    CompanyDTO findCompanyById(Long id) throws CompanyNoSuchElementException;

    void saveCompany(CompanyDTO companyDTO);

    void deleteCompanyById(Long id);
}