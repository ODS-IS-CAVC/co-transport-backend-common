package com.next.logistic.util;

import java.util.Arrays;
import java.util.List;

public final class Constants {

    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";

    public static final String SYSTEM = "system";
    public static final String DEFAULT_LANGUAGE = "en";

    public static final class ROLE_NAME {

        public static final String SYSTEM_ADMIN = "NEXTR-SYSTEM-ADMIN";
        public static final String ADMIN = "NEXTR-ADMIN";

        public static final String M2M_ROLE = "NEXTR-M2M-ROLE";

        public static final List<String> MACHINE_ROLES = Arrays.asList("NEXTR-SYSTEM-ADMIN",
                "NEXTR-M2M-ROLE");

        public static final String NEXTR_PREFIX_ROLE = "NEXTR-";

        public static final String NEXTR_PREFIX_PERMISSION = "NEXTR-";

    }

    public static final List<String> MACHINE_USERS = Arrays.asList("m2m", "machine_to_machine");

    public static final String SYSTEM_ACTOR = "System";

    public static final String KEY_CLAIM_GROUP = "partner";


    public static final class MAPPER {

        public static final String OBJECT_TO_JSON = "objectToJson";

        public static final String JSON_TO_OBJECT = "jsonToObject";
    }

    public static final class DATE {

        public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    }

    public static final class XHTTP {

        public final static String ATTRIBUTE_REQUEST_ID = "X-Request-Id";
        public static final String ATTRIBUTE_START_TIME = "X-start-time";
        public static final String SYS_TOKEN = "Sys-Token";
    }

    public static final class KEYCLOAK{
        public static final String COMPANY_ID= "companyId";
        public static final String SECRET_KEY = "secretKey";
        public static final String SYS_LOGIN = "sysLogin";

        public static final String ADMIN_CLIENT="admin-cli";
        public static final String ADMIN_REALM="master";
        public static final String ADMIN_NAME="admin";
        public static final String PREFIX_ACCOUNT_SERVICE="service-account-";
        public static final String CLIENT_REALM_MANAGEMENT="realm-management";
        public static final String AZP="azp";

    }


}
