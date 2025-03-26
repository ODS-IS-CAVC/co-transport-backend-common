package com.next.logistic.util;

public enum NextError {

    SYSTEM_UNSUPPORT_IDEMPOTENT_EXCPETION(-1,"Idempotent only support Request or Message."),
    SYSTEM_HTTP_DUPLICATE_REQUEST(1000, "The request has proceeded before."),
    SYSTEM_DUPLICATE_MESSAGE(1001, "The message has proceeded before."),
    SYSTEM_HTTP_TIMEOUT(1002, "The response from target resource is over timemout."),
    SYSTEM_UNAUTHORIZATION_FUNCTION(1003, "You do not have permission to perform this function."),
    SYSTEM_VALICATION_UNAUTHORIZATION_DATA(-2,"The function(s) is configured for data authorization , but user has not added data permission in client roles in keycloak therefor the system raises an exception."),
    SYSTEM_UNAUTHORIZATION_DATA(1004, "This Data Object is configured for data authorization but the system does not regconise any data permission"),
    SYSTEM_DATABASE_CONNECTION(1005, "A database connection error has occurred."),

    //Postgresql
    SYSTEM_DATABASE_POSTGRESQL_CONNECTION(1005, "A database connection error has occurred."),
    SYSTEM_DATABASE_POSTGRESQL_QUERY_TIMEOUT(1006, "The query is executed in PostgreSQL is too slow, over configured timeout."),
    SYSTEM_DATABASE_POSTGRESQL_INSERT_TIMEOUT(1007, "Insert to database is too slow, over configured timeout."),
    SYSTEM_DATABASE_POSTGRESQL_UPDATE_TIMEOUT(1008, "Update to database is too slow, over configured timeout."),
    SYSTEM_DATABASE_POSTGRESQL_UNAUTHORIZATION(1009, "PostgreSQL user does not permission to execute."),
    //Mongodb
    SYSTEM_DATABASE_MONGODB_CONNECTION(1010, "A database connection error has occurred."),
    SYSTEM_DATABASE_MONGODB_QUERY_TIMEOUT(1011, "The is executed in MongoDB is too slow, over configured timeout."),
    SYSTEM_DATABASE_MONGODB_INSERT_TIMEOUT(1012, "Insert to database is too slow, over configured timeout."),
    SYSTEM_DATABASE_MONGODB_UPDATE_TIMEOUT(1013, "Update to database is too slow, over configured timeout."),
    SYSTEM_DATABASE_MONGODB_UNAUTHORIZATION(1014, "Mongo user does not permission to execute."),
    //REDIS
    SYSTEM_CACHE_REDIS_CONNECTION(1014, "A database connection error has occurred."),
    SYSTEM_CACHE_REDIS_GET_TIMEOUT(1015, "The is executed in Redis is too slow, over configured timeout."),
    SYSTEM_CACHE_REDIS_INSERT_TIMEOUT(1016, "Insert to database is too slow, over configured timeout."),
    SYSTEM_CACHE_REDIS_UNAUTHORIZATION(1017, "Redis user does not permission to execute."),
    //KAFKA
    SYSTEM_KAFKA_CONNECTION(1018, "A Kafka connection error has occurred."),
    //Third parties services
    SYSTEM_HTTP_BAD_REQUEST(1022," Request body is bad format."),
    SYSTEM_HTTP_BAD_FORMAT(1023," Response body is not json format."),
    SYSTEM_ILLEGAL_DATA_AUTHORIZATION(1031," Data authorization is illegal"),
    SYSTEM_ILLEGAL_ACTIVITY_AUDIT(1032," Configred Data in activityAudit is illegal"),
    SYSTEM_UNSUPPORT_CHANNEL_NOTIFICATION_EXCPETION(1033,"Notification Channel only support WEB or EMAIL."),
    SYSTEM_NOTIFICATION_PARAMETERBODY_BADFORMAT_EXCPETION(1034,"Parameter body invalid format(key,value) WEB or EMAIL."),
    SYSTEM_API_EXCEPTION(1035,"Error call API : %s"),
    SYSTEM_FILE_READ_EXCEPTION(1036, "Don't read file"),
    SYSTEM_AUTHORIZATION_EXCEPTION(1037,"Unauthoirzed"),
    SYSTEM_AUTHORIZATION_MISMATCH(1038,"client and authorization server communication is mismatched."),
    SYSTEM_UNEXPECTED_EXCEPTION(1039,"UnExpected Exception."),
    // File
    SYSTEM_FILE_UNAUTHENTICATION(1040,"UnAuthenticated during connect to File Server(%s)."),
    SYSTEM_FILE_UNAUTHORIZATION(1041,"Do not have permission with file resouces (%s)."),
    SYSTEM_FILE_WRITE_EXCEPTION(1042,"An encounter exception during writing binary to file."),

    SYSTEM_ACTIVITY_USER_EXCEPTION(1043,"Actor's information of activity must be identified."),

    SYSTEM_USER_SERVICE_EXCEPTION(1044, "User service exception"),

    SYSTEM_USER_ADMIN_SERVICE_EXCEPTION(1045, "Admin service exception"),
    SYSTEM_DECRYPT_TOKEN_EXCEPTION(1046, "An encounter exception during decrypt token"),
    SYSTEM_ENCRYPT_TOKEN_EXCEPTION(1047, "An encounter exception during encrypt token"),
    SYSTEM_GENERATE_TOKEN_EXCEPTION(1048, "An encounter exception during generate token"),
    SYSTEM_VERIFY_TOKEN_EXCEPTION(1049, "An encounter exception during verify token"),
    SYSTEM_HASH_DOMAIN_EXCEPTION(1050, "An encounter exception during hash domain"),
    SYSTEM_EXTRACT_DOMAIN_EXCEPTION(1051, "An encounter exception during extract domain"),
    SYSTEM_UPDATE_REFRESH_TOKEN_CONFIG_EXCEPTION(1052, "An encounter exception during config refresh token for client"),
    SYSTEM_UPDATE_CHECK_TOKEN_EXCEPTION(1053, "An encounter exception during check access token for client"),
    UNAUTHORIZED(401, "Unauthorized");



    private final int code;
    private final String description;

    private NextError(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }
}
