package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Form.ResultRecordForm;
import com.h154.saesumMBTI.DTO.Result.ResultRecordDTO;
import com.h154.saesumMBTI.Domain.Result.ResultRecordDomain;
import com.h154.saesumMBTI.Domain.Survey.SurveyDomain;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Enum.ResultRecordState;
import com.h154.saesumMBTI.Response.BasicResponse;
import com.h154.saesumMBTI.Service.ResultRecordService;
import com.h154.saesumMBTI.Service.SurveyService;
import com.h154.saesumMBTI.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ResultRecordController {

    private final ResultRecordService resultRecordService;
    private final UserService userService;
    private final SurveyService surveyService;


    //result record
    @GetMapping("/resultRecord/new")
    public ResponseEntity<BasicResponse> createResultRecord(@Valid ResultRecordForm resultRecordForm, BindingResult result){


        BasicResponse response = new BasicResponse();

        if (result.hasErrors()){

            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("Input Form 오류: "+result.toString())
                    .result(Collections.emptyList())
                    .build();
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

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("결과 기록 생성 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("결과 기록 생성 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @PostMapping("/resultRecord/remove")
    public  ResponseEntity<BasicResponse> removeResultRecord(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try{

            resultRecordService.removeResultRecord(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("결과 기록 삭제 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("결과 기록 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    @PostMapping("/resultRecord/{id}/done")
    public  ResponseEntity<BasicResponse> finishResultRecord(@PathVariable("id") Long id, String body){

        BasicResponse response = new BasicResponse();

        try{

            resultRecordService.finishResultRecord(id, body);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("결과 삭제 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("결과 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }







}
