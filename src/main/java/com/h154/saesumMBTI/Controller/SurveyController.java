package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Form.AnswerOptionForm;
import com.h154.saesumMBTI.Controller.Form.QuestionForm;
import com.h154.saesumMBTI.Controller.Form.UserForm;
import com.h154.saesumMBTI.DTO.AnswerOptionDTO;
import com.h154.saesumMBTI.DTO.QuestionDTO;
import com.h154.saesumMBTI.Domain.Survey.AnswerOptionDomain;
import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Enum.AnswerType;
import com.h154.saesumMBTI.Response.BasicResponse;
import com.h154.saesumMBTI.Service.SurveyService;
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
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;


    //Question
    @PostMapping("/survey/question/new")
    public ResponseEntity<BasicResponse> createQuestion(@Valid QuestionForm form, BindingResult result) {

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


        try {

            QuestionDomain questionDomain = new QuestionDomain();
            questionDomain.SetQuestionDomain(form);
            surveyService.joinQuestion(questionDomain);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("질문 생성 성공")
                    .result(Collections.emptyList())
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("질문 생성 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/survey/question/{id}/remove")
    public ResponseEntity<BasicResponse> removeQuestion(@PathVariable("id") Long id) {

        BasicResponse response = new BasicResponse();

        try {

            surveyService.removeQuestion(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("질문 삭제 성공")
                    .result(Collections.emptyList())
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("질문 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/survey/question/{id}/find")
    public ResponseEntity<BasicResponse> findQuestion(@PathVariable("id") Long id) {

        BasicResponse response = new BasicResponse();

        try {

            QuestionDomain questionDomain = surveyService.findQuestion(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("질문 조회 성공")
                    .result(Arrays.asList(new QuestionDTO(questionDomain)))
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("질문 조회 실패." +e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @GetMapping("/survey/question/{page}/{count}/paging")
    public ResponseEntity<BasicResponse> findQuestionByPage(@PathVariable("page") int page, @PathVariable("count") int count){

        BasicResponse response = new BasicResponse();

        try {

            List<QuestionDTO> questionDTOList = surveyService.findQuestionsByPage(page,count);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("질문 조회 성공")
                    .result(Arrays.asList(questionDTOList))
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("질문 조회 실패." +e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }





    //answer
    @PostMapping("/survey/answerOption/new")
    public ResponseEntity<BasicResponse> createAnswerOption(@Valid AnswerOptionForm form, BindingResult result) {

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


        try {

            AnswerOptionDomain answerOptionDomain = new AnswerOptionDomain();
            answerOptionDomain.SetAnswerDomain(form);
            surveyService.joinAnswer(answerOptionDomain);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("답 선택 생성 성공")
                    .result(Collections.emptyList())
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("답 선택 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/survey/answerOption/{id}/remove")
    public ResponseEntity<BasicResponse> removeAnswerOption(@PathVariable("id") Long id) {

        BasicResponse response = new BasicResponse();

        try {

            surveyService.removeAnswerOption(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("답 삭제 성공")
                    .result(Collections.emptyList())
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("답 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }

        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @GetMapping("/survey/answerOption/{id}/find")
    public ResponseEntity<BasicResponse> findAnswerOption(@PathVariable("id") Long id) {

        BasicResponse response = new BasicResponse();

        try {

            AnswerOptionDTO answerOptionDTO = surveyService.findAnswer(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("답 선택 성공")
                    .result(Arrays.asList(answerOptionDTO))
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("답  산택 조회 실패." +e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }

        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @GetMapping("/survey/answerOption/{type}/{page}/{count}/paging")
    public ResponseEntity<BasicResponse> findAnswerOptionByPage(@PathVariable("type") AnswerType answerType, @PathVariable("page") int page, @PathVariable("count") int count){

        BasicResponse response = new BasicResponse();

        try {

            List<AnswerOptionDTO> answerOptionDTOList = surveyService.findAnswersOptionByType(answerType,page,count);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("답 유형 조회  성공")
                    .result(Arrays.asList(answerOptionDTOList))
                    .build();
        } catch (Exception e) {

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("답 유형 조회 실패." +e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }




}
