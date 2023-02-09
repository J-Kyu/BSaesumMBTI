package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Form.UserForm;
import com.h154.saesumMBTI.DTO.UserDTO;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Response.*;
import com.h154.saesumMBTI.Response.UserReponse.OKResponseUserDTO;
import com.h154.saesumMBTI.Service.UserService;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/api")
public class UserController {

    private final UserService userService;


    @Operation(summary = "회원 생성 요청", description = "회원가입을 합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseUserDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value="/user/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(
            @Valid UserForm form,
            BindingResult result
    )
    {


        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }


        try {
            UserDomain userDomain = new UserDomain(); //준영속 상태....persist 를 해야 영속상태가 된다
            userDomain.SetDomainData(form);

            userService.join(userDomain);
            OKResponseUserDTO okResponse = new OKResponseUserDTO("회원 생성 성공", new UserDTO(userDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("회원 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());

        }

    }




    @Operation(summary = "회원 삭제 요청", description = "회원 탈퇴 합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/user/{id}/withdraw")
    public ResponseEntity withdraw(
            @PathVariable("id") Long id
    ){

        try {

            userService.deleteUserDomain(id);
            OkResponse okResponse = new OkResponse("회원 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("회원 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());

        }

    }


    @Operation(summary = "회원 조회 요청", description = "회원 조회 합니다.", tags = { "User Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseUserDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/user/{nickname}/find")
    public ResponseEntity find(
            @ApiParam(value = "사용자 닉네임") @PathVariable("nickname") String nickname){



        try {

            UserDomain userDomain = userService.findUserByNickname(nickname);
            OKResponseUserDTO okResponse = new OKResponseUserDTO("회원 조회 성공", new UserDTO(userDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){

            InternalServerErrorResponse response = new InternalServerErrorResponse("회원 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }



    }


}
