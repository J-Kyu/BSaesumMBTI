package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Body.FinishResultRecordBody;
import com.h154.saesumMBTI.Controller.Form.ResultRecordForm;
import com.h154.saesumMBTI.DTO.Result.ResultRecordDTO;
import com.h154.saesumMBTI.DTO.UserDTO;
import com.h154.saesumMBTI.Domain.Result.ResultRecordDomain;
import com.h154.saesumMBTI.Domain.Survey.SurveyDomain;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Enum.ResultRecordState;
import com.h154.saesumMBTI.Response.BadRequestResponse;
import com.h154.saesumMBTI.Response.BasicResponse;
import com.h154.saesumMBTI.Response.InternalServerErrorResponse;
import com.h154.saesumMBTI.Response.OkResponse;
import com.h154.saesumMBTI.Response.ResultResponse.OKResponseResultRecordDTO;
import com.h154.saesumMBTI.Response.UserReponse.OKResponseUserDTO;
import com.h154.saesumMBTI.Service.ResultRecordService;
import com.h154.saesumMBTI.Service.SurveyService;
import com.h154.saesumMBTI.Service.UserService;
import io.swagger.models.Response;
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
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/api")
public class ResultRecordController {

    private final ResultRecordService resultRecordService;
    private final UserService userService;
    private final SurveyService surveyService;


    //result record
    @Operation(summary = "결과 기록 생성 요청", description = "결과 기록 생성 합니다.", tags = { "Result Record Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseResultRecordDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/resultRecord/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createResultRecord(
            @Valid ResultRecordForm resultRecordForm,
            BindingResult result
    ){

        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

        try{

            ResultRecordDomain resultRecordDomain = new ResultRecordDomain();
            resultRecordDomain.setResultRecordState(ResultRecordState.IN_PROGRESS);

            UserDomain userDomain = userService.findUserDomain(resultRecordForm.getUserId());
            SurveyDomain surveyDomain = surveyService.findSurveyDomain(resultRecordForm.getSurveyId());

            resultRecordDomain.setSurveyDomain(surveyDomain);
            resultRecordDomain.setUser(userDomain);

            resultRecordService.joinResultRecord(resultRecordDomain);

            OKResponseResultRecordDTO okResponse = new OKResponseResultRecordDTO("결과 기록 생성 성공", new ResultRecordDTO(resultRecordDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("결과 기록 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "결과 기록 조회 요청", description = "결과 조회 합니다.", tags = { "Result Record Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseResultRecordDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/resultRecord/{id}/find")
    public ResponseEntity findResultRecord(@PathVariable("id") Long id){
        try{
            ResultRecordDTO resultRecordDTO = resultRecordService.findResultRecord(id);
            OKResponseResultRecordDTO okResponse = new OKResponseResultRecordDTO("결과 기록 조회 성공", resultRecordDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("결과 기록 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }



    @Operation(summary = "결과 기록 삭제 요청", description = "결과 기록 삭제 합니다.", tags = { "Result Record Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/resultRecord/remove")
    public  ResponseEntity removeResultRecord(@PathVariable("id") Long id){

        try{
            resultRecordService.removeResultRecord(id);
            OkResponse okResponse = new OkResponse("결과 기록 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("회원 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }



    @Operation(summary = "결과 기록 완료 요청", description = "결과 기록 완료 합니다.", tags = { "Result Record Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseResultRecordDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PatchMapping("/resultRecord/{id}/done")
    public  ResponseEntity finishResultRecord(
            @PathVariable("id") Long id,
            FinishResultRecordBody body
    ){

        try{
            ResultRecordDTO resultRecordDTO = resultRecordService.finishResultRecord(id, body);
            OKResponseResultRecordDTO okResponse = new OKResponseResultRecordDTO("결과 기록 완료 성공", resultRecordDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("결과 기록 완료 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }







}
