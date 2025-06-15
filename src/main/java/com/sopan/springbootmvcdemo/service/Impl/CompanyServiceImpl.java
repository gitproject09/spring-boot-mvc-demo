package com.sopan.springbootmvcdemo.service.Impl;

import com.sopan.springbootmvcdemo.dto.CompanyDTO;
import com.sopan.springbootmvcdemo.entity.Company;
import com.sopan.springbootmvcdemo.exception.CompanyNoSuchElementException;
import com.sopan.springbootmvcdemo.mapper.CompanyMapper;
import com.sopan.springbootmvcdemo.repository.CompanyRepository;
import com.sopan.springbootmvcdemo.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * Implementation of the CompanyService interface.
 * Handles business logic for company operations.
 */
@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;
    private MessageSource messageSource;

    @Override
    public Page<CompanyDTO> findPaginatedCompanies(int pageNum, int pageSize) {
        // Create a Pageable object for pagination. Note: page numbers are typically 0-indexed in Spring Data.
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);
        Page<Company> page = companyRepository.findAll(pageable);
        // Map the Page of Company entities to a Page of CompanyDTOs.
        return page.map(companyMapper::mapToCompanyDTO);
    }

    @Override
    public Page<CompanyDTO> findPaginatedCompaniesByName(String name, Pageable pageable) {
        // Find companies by name containing the keyword, using the provided Pageable.
        Page<Company> page = companyRepository.findByNameContaining(name, pageable);
        // Map the results to DTOs.
        return page.map(companyMapper::mapToCompanyDTO);
    }

    @Override
    public CompanyDTO findCompanyById(Long id) {
        // Retrieve a company by its ID.
        Company company = companyRepository.findById(id).orElseThrow(() -> {
            // If not found, construct a localized error message and throw a custom exception.
            String message = messageSource.getMessage("entity.notfound", new Object[]{id}, Locale.getDefault());
            return new CompanyNoSuchElementException(message, id);
        });
        // Map the found entity to a DTO.
        return companyMapper.mapToCompanyDTO(company);
    }

    @Override
    public void saveCompany(CompanyDTO companyDTO) {
        // Map the DTO to an entity.
        Company company = companyMapper.mapToCompany(companyDTO);
        // Save the entity to the database.
        companyRepository.save(company);
    }

    @Override
    public void deleteCompanyById(Long id) {
        // Delete the company with the given ID.
        companyRepository.deleteById(id);
    }
}
