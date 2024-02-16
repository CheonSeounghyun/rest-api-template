package com.wini.restapitemplate.user;

import com.wini.restapitemplate.api.ApiResponse;
import com.wini.restapitemplate.api.ApiResponseConstants;
import com.wini.restapitemplate.user.model.User;
import com.wini.restapitemplate.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserApiController {

    @Autowired
    UserService userService;

    //모든 사용자 검색
    @GetMapping(value = "/api/user")
    public ResponseEntity<Object> getUsers(){
        return null;
    }

    //특정 사용자 검색
    @GetMapping(value = "/api/user/{id}")
    public ResponseEntity<Object> getUser(@ModelAttribute User user){

        try {
            User result = userService.getUser(user);

            if(result != null){
                return ResponseEntity.status(200).body(new ApiResponse(ApiResponseConstants.STAUTS_SUCCESS, ApiResponseConstants.MESSAGE_DATA_FOUND, result));
            }else{
                Map<String, Object> emptyData = Collections.emptyMap(); //비어있는 객체를 생성해서 data로 추가한다.
                return ResponseEntity.status(404).body(new ApiResponse(ApiResponseConstants.STATUS_FALSE, String.format(ApiResponseConstants.MESSAGE_DATA_NOT_FOUND, user.getId()), emptyData));
            }

        } catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(ApiResponseConstants.STATUS_ERROR, ApiResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    //사용자 추가
    @PostMapping(value = "/api/user/{id}")
    public ResponseEntity<Object> insertUser(@RequestBody User user){

        try {
            int result = userService.insertUser(user);

            if(result > 0){
                return ResponseEntity.status(204).body(new ApiResponse(ApiResponseConstants.STAUTS_SUCCESS, ApiResponseConstants.MESSAGE_DATA_INSERT_SUCCESS, user));
            }else{
                Map emptyData = new HashMap(); //비어있는 객체를 생성해서 data로 추가한다.
                return ResponseEntity.status(404).body(new ApiResponse(ApiResponseConstants.STATUS_FALSE, String.format(ApiResponseConstants.MESSAGE_DATA_INSERT_FALED, user.getId()), emptyData));
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(ApiResponseConstants.STATUS_ERROR, ApiResponseConstants.MESSAGE_INTERNAL_SERVER_ERROR, e.getMessage()));
        }
    }

    //특정 사용자 업데이트
    @PutMapping(value = "/api/user/{id}")
    public ResponseEntity<Object> updateUser(@ModelAttribute User user){
        return null;
    }

    //특정 사용자 제거
    @DeleteMapping(value = "/api/user/{id}")
    public ResponseEntity<Object> deleteUser(@ModelAttribute User user){
        return null;
    }
}
