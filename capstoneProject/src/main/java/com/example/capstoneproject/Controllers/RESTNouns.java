package com.example.capstoneproject.Controllers;

/**
 * Stores API paths and REST nouns as constants to ensure consistency across REST controllers.
 */
public class RESTNouns {
    /**
     * API versioning constant.
     */
    public static final String VERSION_1 = "/v1";

    /**
     * General path for identifying resources by ID.
     */
    public static final String ID = "/{id}";

    // Specific resource paths

    /**
     * Path for administrator-related resources.
     */
    public static final String ADMINS = "/admin";

    /**
     * Path for agent-related resources.
     */
    public static final String AGENTS = "/agent";

    /**
     * Path for customer-related resources.
     */
    public static final String CUSTOMERS = "/customer";

    /**
     * Path for home-related resources.
     */
    public static final String HOMES = "/home";

    /**
     * Path for policy-related resources.
     */
    public static final String POLICIES = "/policy";

    /**
     * Path for quote-related resources.
     */
    public static final String QUOTES = "/quote";

    /**
     * Path for user-related resources.
     */
    public static final String USERS = "/user";

    /**
     * Path for vehicle-related resources.
     */
    public static final String VEHICLES = "/vehicle";

    /**
     * Path for auto insurance quote-related resources.
     */
    public static final String AUTOQUOTE = "/auto_quote";

    /**
     * Path for home insurance quote-related resources.
     */
    public static final String HOMEQUOTE = "/home_quote";
}
