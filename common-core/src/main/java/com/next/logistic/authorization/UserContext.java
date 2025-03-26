package com.next.logistic.authorization;

import com.next.logistic.annotation.AllowQuery;
import com.next.logistic.util.Constants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public class UserContext implements Cloneable {

    @AllowQuery
    private User user;

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public UserContext() {
        this.user = new User();
    }

    public void setUser(User user) {
        this.user = user;
    }


    public boolean hasAnyPermission(String... roles) {
        return getUser().getClientRoles().stream()
                .anyMatch(Set.of(roles)::contains);
    }

    public boolean startWithAnyPermission(String... roles) {
        return getUser().getClientRoles().stream().anyMatch(s -> {
            for (String role : roles) {
                if (s.startsWith(role)) {
                    return true;
                }
            }
            return false;
        });

    }

    public boolean hasPermissionNor(String permission, String permissionNotIn) {
        return (getUser().getClientRoles().contains(permission) && !getUser().getClientRoles()
                .contains(permissionNotIn));
    }

    public boolean hasAnyRole(String... roles) {
        return getUser().getRealmRoles().stream()
                .anyMatch(Set.of(roles)::contains);
    }

//    public boolean isSystemAdmin() {
//        return Constants.MACHINE_USERS.contains(getUser().getUsername())
//                || getUser().getRealmRoles().contains(Constants.ROLE_NAME.ADMIN);
//    }

    public boolean isM2MRole() {
        return Constants.MACHINE_USERS.contains(getUser().getUsername())
                || getUser().getRealmRoles().contains(Constants.ROLE_NAME.M2M_ROLE);
    }

    public boolean isXUser() {
        return getUser().getRealmRoles().stream()
                .anyMatch(s -> s.startsWith(Constants.ROLE_NAME.NEXTR_PREFIX_ROLE));
    }

    @Override
    @SneakyThrows
    public Object clone() {
        return super.clone();
    }
}
