package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Body.SurveyBody;
import com.h154.saesumMBTI.Controller.Form.AnswerOptionForm;
import com.h154.saesumMBTI.Controller.Form.QuestionForm;
import com.h154.saesumMBTI.DTO.Survey.AnswerOptionDTO;
import com.h154.saesumMBTI.DTO.Survey.QuestionDTO;
import com.h154.saesumMBTI.DTO.Survey.SurveyDTO;
import com.h154.saesumMBTI.Domain.Survey.AnswerOptionDomain;
import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import com.h154.saesumMBTI.Enum.AnswerType;
import com.h154.saesumMBTI.Response.BadRequestResponse;
import com.h154.saesumMBTI.Response.InternalServerErrorResponse;
import com.h154.saesumMBTI.Response.OkResponse;
import com.h154.saesumMBTI.Response.SurveyResponse.OKResponseAnswerOptionDTO;
import com.h154.saesumMBTI.Response.SurveyResponse.OKResponseQuestionDTO;
import com.h154.saesumMBTI.Response.SurveyResponse.OKResponseSurveyDTO;
import com.h154.saesumMBTI.Response.UserReponse.OKResponseUserDTO;
import com.h154.saesumMBTI.Service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/admin/api")
@CrossOrigin(origins = {"*"})
public class SurveyController {

    private final SurveyService surveyService;


    //Question
    @Operation(summary = "질문 생성 요청", description = "질문 생성 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseQuestionDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/survey/question/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createQuestion(
            @Valid QuestionForm form,
            BindingResult result
    ){


        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

        try {

            QuestionDomain questionDomain = new QuestionDomain();
            questionDomain.SetQuestionDomain(form);
            surveyService.joinQuestion(questionDomain);
            OKResponseQuestionDTO okResponse = new OKResponseQuestionDTO("질문 생성 성공", new QuestionDTO(questionDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("질문 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "질문 삭제 요청", description = "질문 삭제 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/survey/question/{id}/remove")
    public ResponseEntity removeQuestion(
            @PathVariable("id") Long id
    ){


        try {
            surveyService.removeQuestion(id);
            OkResponse okResponse = new OkResponse("질문 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("질문 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "질문 조회 요청", description = "질문 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseQuestionDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/survey/question/{id}/find")
    public ResponseEntity findQuestion(
            @PathVariable("id") Long id
    ){

        try {

            QuestionDTO questionDTO = surveyService.findQuestion(id);
            OKResponseQuestionDTO okResponse = new OKResponseQuestionDTO("질문 조회 성공", questionDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());


        } catch (Exception e) {

            InternalServerErrorResponse response = new InternalServerErrorResponse("질문 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }


    @Operation(summary = "질문 페이지 조회 요청", description = "질문 페이지 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseQuestionDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/survey/question/{page}/{count}/paging")
    public ResponseEntity findQuestionByPage(
            @PathVariable("page") int page,
            @PathVariable("count") int count
    ){

        try {
            List<QuestionDTO> questionDTOList = surveyService.findQuestionsByPage(page,count);
            OKResponseQuestionDTO okResponse = new OKResponseQuestionDTO("질문 페이지 조회 성공",questionDTOList);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("질문 페이지 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }


    //answer
    @Operation(summary = "답 생성 요청", description = "답 생성 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseAnswerOptionDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/survey/answerOption/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAnswerOption(
            @Valid AnswerOptionForm form,
            BindingResult result
    ) {



        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }



        try {

            AnswerOptionDomain answerOptionDomain = new AnswerOptionDomain();
            answerOptionDomain.SetAnswerDomain(form);
            surveyService.joinAnswer(answerOptionDomain);

            OKResponseAnswerOptionDTO okResponse = new OKResponseAnswerOptionDTO("답 생성 성공", new AnswerOptionDTO(answerOptionDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("답 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    @Operation(summary = "답 삭제 요청", description = "답 삭제 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/survey/answerOption/{id}/remove")
    public ResponseEntity removeAnswerOption(
            @PathVariable("id") Long id
    ){


        try {
            surveyService.removeAnswerOption(id);
            OKResponseUserDTO okResponse = new OKResponseUserDTO("답 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("답 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }


    @Operation(summary = "답 조회 요청", description = "답 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseAnswerOptionDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/survey/answerOption/{id}/find")
    public ResponseEntity findAnswerOption(@PathVariable("id") Long id) {

        try {

            AnswerOptionDTO answerOptionDTO = surveyService.findAnswer(id);
            OKResponseAnswerOptionDTO okResponse = new OKResponseAnswerOptionDTO("답 조회 성공", answerOptionDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("답 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());

        }
    }


    @Operation(summary = "답 페이지 조회 요청", description = "답 페이지 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseAnswerOptionDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/survey/answerOption/{type}/{page}/{count}/paging")
    public ResponseEntity findAnswerOptionByPage(
            @PathVariable("type") AnswerType answerType,
            @PathVariable("page") int page,
            @PathVariable("count") int count)
    {

        try {

            List<AnswerOptionDTO> answerOptionDTOList = surveyService.findAnswersOptionByType(answerType,page,count);
            OKResponseAnswerOptionDTO okResponse = new OKResponseAnswerOptionDTO("답 페이지 조회 성공", answerOptionDTOList);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("답 페이지 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    //survey
    @Operation(summary = "설문 생성 요청", description = "설문 생성 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseSurveyDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping("/survey/new")
    public ResponseEntity createSurvey(
            @RequestBody SurveyBody surveyBody
    ) {


        try {
            SurveyDTO surveyDTO = surveyService.joinSurvey(surveyBody);
            OKResponseSurveyDTO okResponse = new OKResponseSurveyDTO("설문 생성 성공", surveyDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("설문 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "설문 삭제 요청", description = "설문 삭제 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/survey/{id}/remove")
    public ResponseEntity removeSurvey(
            @PathVariable("id") Long id
    ){

        try {

            surveyService.removeSurvey(id);
            OkResponse okResponse = new OkResponse("설문 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        } catch (Exception e) {

            InternalServerErrorResponse response = new InternalServerErrorResponse("설문 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }


    @Operation(summary = "설문 조회 요청", description = "설문 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseSurveyDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/survey/{id}/find")
    public ResponseEntity findWithId(
            @PathVariable("id") Long id
    ) {


        try {
            SurveyDTO surveyDTO = surveyService.findSurveyWithId(id);
            OKResponseSurveyDTO okResponse = new OKResponseSurveyDTO("설문 조회 성공", surveyDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("설문 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }


    @Operation(summary = "설문 제목으로 조회 요청", description = "설문 제목으로 조회 합니다.", tags = { "Survey Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseSurveyDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/survey/{title}/findWithTitle")
    public ResponseEntity findWithTitle(
            @PathVariable("title") String title
    ){


        try {

            SurveyDTO surveyDTO = surveyService.findSurveyWithTitle(title);
            OKResponseSurveyDTO okResponse = new OKResponseSurveyDTO("설문 제목으로 조회 성공", surveyDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("설문 제목으로 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }





}
