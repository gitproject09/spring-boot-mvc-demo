package com.sopan.springbootmvcdemo.dto;

import com.sopan.springbootmvcdemo.enums.Sector;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for company information.
 * This record encapsulates company data that is transferred between layers of the application,
 * particularly between the service layer and the presentation layer (controllers/views).
 *
 * @param id The unique identifier of the company.
 * @param name The name of the company.
 * @param country The country where the company is based.
 * @param CEO The name of the Chief Executive Officer of the company.
 * @param foundationDate The date when the company was founded.
 * @param revenue The annual revenue of the company.
 * @param sector The economic sector to which the company belongs.
 */
public record CompanyDTO(
    Long id,
    String name,
    String country,
    String CEO,
    LocalDate foundationDate,
    Long revenue,
    Sector sector
) {
    // No additional methods or customizations needed for this simple DTO record.
    // Javadoc for record components is typically placed on the component itself in the record header.
}
