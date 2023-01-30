package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Form.UserForm;
import com.h154.saesumMBTI.DTO.UserDTO;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Response.BasicResponse;
import com.h154.saesumMBTI.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/user/new")
    public ResponseEntity<BasicResponse> create(@Valid UserForm form, BindingResult result){

        BasicResponse response = new BasicResponse();

        if (result.hasErrors()){

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("Input Form 오류: "+result.toString())
                    .result(Collections.emptyList())
                    .build();
            return new ResponseEntity<>(response,response.getHttpStatus());
        }


        try {

            UserDomain userDomain = new UserDomain(form); //준영속 상태....persist 를 해야 영속상태가 된다

            userService.join(userDomain);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("회원 생성 성공")
                    .result(Collections.emptyList())
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("회원 생성 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }



        return new ResponseEntity<>(response,response.getHttpStatus());
    }

}
