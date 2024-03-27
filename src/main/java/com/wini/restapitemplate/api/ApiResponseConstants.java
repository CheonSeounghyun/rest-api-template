package com.wini.restapitemplate.api;

public class ApiResponseConstants {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";
    public static final String STATUS_ERROR = "error";

    public static final String MESSAGE_INTERNAL_SERVER_ERROR = "An internal server error occurred.";

    public static final String MESSAGE_USER_DUPLICATE_ID = "Already registered id.";

    public static final String MESSAGE_DATA_SELECT_SUCCESS = "%s information retrieved.";
    public static final String MESSAGE_DATA_NOT_EXIST = "No %s information found.";
    public static final String MESSAGE_DATA_INSERT_SUCCESS = "%s information added.";
    public static final String MESSAGE_DATA_UPDATE_SUCCESS = "%s information updated.";
    public static final String MESSAGE_DATA_DELETE_SUCCESS = "%s information deleted.";

    public static final String MESSAGE_API_ACCESS_DENIED = "Invalid API KEY.";
    public static final String MESSAGE_API_KEY_CREATE_SUCCESS = "API KEY created.";
    public static final String MESSAGE_API_KEY_EXPIRE_SUCCESS = "API KEY expired.";
}