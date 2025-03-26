package com.next.logistic.authorization;

import com.next.logistic.annotation.AllowQuery;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@NoArgsConstructor
public class User {

    @AllowQuery
    private String username;

    @AllowQuery
    private String preferredUserName;

    @AllowQuery
    private String email;

    @AllowQuery
    private String fullName;

    @AllowQuery
    private String firstName;

    @AllowQuery
    private String lastName;

    @AllowQuery
    private String id;

    @AllowQuery
    private String companyId;

    @AllowQuery
    private String companyCode;

    @AllowQuery
    private boolean debug;

    @AllowQuery
    private boolean shipper;

    @AllowQuery
    private String systemId;

    @AllowQuery
    private String secretKey;

    @AllowQuery
    private Set<String> clientRoles = new HashSet<>();//permissions

    @AllowQuery
    private Set<String> realmRoles = new HashSet<>();//realm roles in keycloak

    @AllowQuery
    private Map<String, Set<String>> partnerPermission = new HashMap<>();

    @AllowQuery
    private Set<String> authorityRoles = new HashSet<>();

    @AllowQuery
    private Map<String, List<String>> attributes = new HashMap<>();

    @AllowQuery
    private List<String> groups = new ArrayList<>(); //Group of user

    @AllowQuery
    private Map<String, Object> metadata = new HashMap<>();
}
