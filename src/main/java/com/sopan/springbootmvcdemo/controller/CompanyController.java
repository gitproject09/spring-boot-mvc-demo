package com.sopan.springbootmvcdemo.controller;

import com.sopan.springbootmvcdemo.dto.CompanyDTO;
import com.sopan.springbootmvcdemo.entity.Company; // Keep this import if Company is used for model attribute typing
import com.sopan.springbootmvcdemo.enums.Sector;
import com.sopan.springbootmvcdemo.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static com.sopan.springbootmvcdemo.util.Constants.*;

/**
 * Controller for handling company-related web requests.
 * Manages displaying company lists, individual company details,
 * and forms for adding, editing, and deleting companies.
 * Uses Thymeleaf for view rendering.
 */
@AllArgsConstructor
@Controller
@RequestMapping("/companies") // Base path for all company-related actions
public class CompanyController {

    private final CompanyService companyService; // Service layer dependency for company operations

    /**
     * Displays the home page, which defaults to the first page of paginated companies.
     * Also handles initial keyword search from a general entry point.
     */
    @GetMapping
    public String displayHomePage(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        // Delegates to the paginated display method, starting at page 1 (as defined by PAGE_NUMBER)
        return displayPaginatedCompanies(model, PAGE_NUMBER, keyword);
    }

    /**
     * Displays a specific page of companies, optionally filtered by a keyword.
     * Handles pagination and search functionality.
     */
    @GetMapping("/page/{pageNum}")
    public String displayPaginatedCompanies(Model model,
                                            @PathVariable(value = "pageNum") int pageNum,
                                            @RequestParam(name = "keyword", required = false) String keyword) {

        Page<CompanyDTO> page; // Holds the paginated list of companies

        // Check if a keyword is provided for searching
        if (keyword != null && !keyword.trim().isEmpty() && !"null".equalsIgnoreCase(keyword)) {
            // If keyword exists, fetch paginated companies filtered by name
            page = companyService.findPaginatedCompaniesByName(keyword, PageRequest.of(pageNum - 1, PAGE_SIZE));
        } else {
            // Otherwise, fetch all paginated companies
            page = companyService.findPaginatedCompanies(pageNum, PAGE_SIZE);
        }

        List<CompanyDTO> companyList = page.getContent(); // Get the list of companies for the current page

        // Add pagination and company data to the model for the view
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("companyList", companyList);
        model.addAttribute("keyword", keyword); // Pass the current keyword back to the view

        return COMPANIES_PAGE; // Return the view name for displaying companies
    }

    /**
     * Displays details for a single company identified by its ID.
     */
    @GetMapping("/{id}")
    public String displayCompanyDetails(@PathVariable Long id, Model model) {
        // Fetch company by ID and add it to the model
        model.addAttribute("company", companyService.findCompanyById(id));
        return VIEW_COMPANY_PAGE; // Return the view name for single company details
    }

    /**
     * Displays the form for adding a new company.
     */
    @GetMapping("/new")
    public String displayAddCompanyForm(Model model) {
        // Add a new Company object to bind form data
        model.addAttribute("company", new CompanyDTO()); // Use CompanyDTO for form backing
        // Add available sectors for a dropdown in the form
        model.addAttribute("sectors", Sector.values());
        return ADD_COMPANY_PAGE; // Return the view name for the add company form
    }

    /**
     * Handles the submission of the new company form.
     * Saves the new company and redirects to the first page of the company list.
     */
    @PostMapping
    public String saveCompany(@ModelAttribute("company") CompanyDTO companyDTO) {
        // Ensure a default sector if none is provided (or handle as a validation error)
        if (companyDTO.getSector() == null || companyDTO.getSector().toString().isEmpty()) {
            companyDTO.setSector(Sector.DFAULT_SECTOR); // Consider making DFAULT_SECTOR a proper enum or null
        }
        companyService.saveCompany(companyDTO); // Save the company
        // Redirect to the first page of companies after saving
        return "redirect:" + COMPANIES_REDIRECT + "/page/1";
    }

    /**
     * Displays the form for editing an existing company.
     */
    @GetMapping("/{id}/edit")
    public String displayEditCompanyForm(@PathVariable Long id, Model model,
                                         @RequestParam(value = "keyword", required = false) String keyword,
                                         @RequestParam(value = "page", defaultValue = "1") int page) {
        CompanyDTO companyDTO = companyService.findCompanyById(id); // Fetch company to edit
        model.addAttribute("company", companyDTO); // Add company to model for form binding
        model.addAttribute("sectors", Sector.values()); // Add sectors for dropdown
        model.addAttribute("page", page); // Keep track of the current page for redirect after update
        model.addAttribute("keyword", keyword); // Keep track of the current keyword

        return EDIT_COMPANY_PAGE; // Return the view name for the edit company form
    }

    /**
     * Handles the submission of the company update form.
     * Updates the company details and redirects to the appropriate page of the company list,
     * preserving any search keyword.
     */
    @PostMapping("/{id}/update")
    public String updateCompany(@ModelAttribute("company") CompanyDTO companyDTO,
                                @PathVariable Long id, // ID from path, companyDTO also has ID
                                @RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(defaultValue = "1") int page,
                                RedirectAttributes redirectAttributes) {
        // companyDTO should have the id set from the form if using @ModelAttribute effectively
        companyService.saveCompany(companyDTO); // Save (update) the company

        // If a keyword was part of the context, add it to redirect attributes
        if (keyword != null && !keyword.trim().isEmpty()) {
            redirectAttributes.addAttribute("keyword", keyword);
        }
        // Redirect to the page the user was on, with the keyword if applicable
        return "redirect:" + COMPANIES_REDIRECT + "/page/" + page;
    }

    /**
     * Handles the deletion of a company.
     * Deletes the company and redirects to the company list (page may need adjustment).
     */
    @GetMapping("/{id}/delete")
    public String deleteCompany(@PathVariable Long id,
                                @RequestParam(name = "page", defaultValue = "0") int page) { // page might be 0 if not from paginated list
        companyService.deleteCompanyById(id); // Delete the company
        // Redirect to the company list; if page was 0, redirect to page 1 or a sensible default
        // Or, better, ensure 'page' is always a valid page number when coming from a list.
        String redirectPage = (page <= 0) ? "1" : String.valueOf(page);
        return "redirect:" + COMPANIES_REDIRECT + "/page/" + redirectPage;
    }
}
