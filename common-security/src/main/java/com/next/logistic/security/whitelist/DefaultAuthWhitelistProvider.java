package com.next.logistic.security.whitelist;


public class DefaultAuthWhitelistProvider implements AuthWhitelistProvider {

    public String[] authWhitelist() {
        return new String[0];
    }

}
