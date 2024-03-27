package com.wini.restapitemplate.user.controller;

import com.wini.restapitemplate.api.ApiErrorResponse;
import com.wini.restapitemplate.api.ApiResponse;
import com.wini.restapitemplate.api.ApiResponseConstants;
import com.wini.restapitemplate.user.model.User;
import com.wini.restapitemplate.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    private final String resourceType = "User";

    // Search all user
    @GetMapping(value = "/api/user")
    public ResponseEntity<Object> getUsers(@ModelAttribute User user){

        List<User> result = userService.getUsers(user);

        return ResponseEntity.status(200).body(new ApiResponse(ApiResponseConstants.STATUS_SUCCESS, String.format(ApiResponseConstants.MESSAGE_DATA_SELECT_SUCCESS, resourceType), result));

    }

    // Search user
    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<Object> getUser(@PathVariable("id") String userId){

        User result = userService.getUser(userId);

        if (result != null) {
            return ResponseEntity.status(200).body(new ApiResponse(ApiResponseConstants.STATUS_SUCCESS, String.format(ApiResponseConstants.MESSAGE_DATA_SELECT_SUCCESS, resourceType),result));
        } else {
            return ResponseEntity.status(200).body(new ApiResponse(ApiResponseConstants.STATUS_SUCCESS, String.format(ApiResponseConstants.MESSAGE_DATA_NOT_EXIST, resourceType), null));
        }

    }

    // Add user
    @PostMapping(value = "/api/user")
    public ResponseEntity<Object> insertUser(@RequestBody User user){

        User result = userService.getUser(user.getUser_id());

        if (result != null) {
            return ResponseEntity.status(200).body(new ApiErrorResponse(ApiResponseConstants.STATUS_FAIL, ApiResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR, ApiResponseConstants.MESSAGE_USER_DUPLICATE_ID));
        } else {
            userService.insertUser(user);
            return ResponseEntity.status(200).body(new ApiResponse(ApiResponseConstants.STATUS_SUCCESS, String.format(ApiResponseConstants.MESSAGE_DATA_INSERT_SUCCESS, resourceType), user));
        }
    }

    // Update user
    @PutMapping(value = "/api/user")
    public ResponseEntity<Object> updateUser(@RequestBody User user){

        userService.updateUser(user);

        return ResponseEntity.status(200).body(new ApiResponse(ApiResponseConstants.STATUS_SUCCESS, String.format(ApiResponseConstants.MESSAGE_DATA_UPDATE_SUCCESS, resourceType), user));

    }

    // Delete user
    @DeleteMapping(value = "/api/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String userId){

        userService.deleteUser(userId);

        return ResponseEntity.status(200).body(new ApiResponse(ApiResponseConstants.STATUS_SUCCESS, String.format(ApiResponseConstants.MESSAGE_DATA_DELETE_SUCCESS, resourceType), null));
    }
}
