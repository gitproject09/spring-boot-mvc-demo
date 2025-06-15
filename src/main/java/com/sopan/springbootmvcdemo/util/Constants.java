package com.sopan.springbootmvcdemo.util;

/**
 * Utility class holding application-wide constants.
 * This class is not meant to be instantiated.
 */
public final class Constants { // Added final to prevent instantiation, common for constant holders

    /** Private constructor to prevent instantiation. */
    private Constants() {
        // This class should not be instantiated.
    }

    /** Base redirect path for company-related operations. */
    public static final String COMPANIES_REDIRECT = "/companies";

    /** View name for the page displaying a list of companies. */
    public static final String COMPANIES_PAGE = "/companies/companies";

    /** View name for the page used to add a new company. */
    public static final String ADD_COMPANY_PAGE = "/companies/AddCompany";

    /** View name for the page used to edit an existing company. */
    public static final String EDIT_COMPANY_PAGE = "/companies/EditCompany";

    /** View name for the page displaying details of a single company. */
    public static final String VIEW_COMPANY_PAGE = "/companies/company";

    /** Default page number for pagination, typically the first page. */
    public static final int PAGE_NUMBER = 1;

    /** Default page size for pagination, representing the number of items per page. */
    public static final int PAGE_SIZE = 10;
}
