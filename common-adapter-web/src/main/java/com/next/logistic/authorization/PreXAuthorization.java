package com.next.logistic.authorization;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class PreXAuthorization {

    @Resource(name = "userContext")
    UserContext userContext;

    public Boolean hasAnyPermission(String... roles) {

        return userContext.hasAnyPermission(roles);
    }

    public boolean startWithAnyPermission(String... roles) {
        return userContext.startWithAnyPermission(roles);
    }

    public boolean hasAnyRole(String... roles) {
        return userContext.hasAnyRole(roles);
    }

//    public Boolean isSystemAdmin() {
//        return userContext.isSystemAdmin();
//    }

    public Boolean isM2MRole() {
        return userContext.isM2MRole();
    }

    public Boolean isXUser() {
        return userContext.isXUser();
    }
}
