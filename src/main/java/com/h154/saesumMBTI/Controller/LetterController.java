package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Body.LetterBody;
import com.h154.saesumMBTI.Controller.Form.LetterForm;
import com.h154.saesumMBTI.DTO.LetterDTO;
import com.h154.saesumMBTI.DTO.Survey.QuestionDTO;
import com.h154.saesumMBTI.Domain.LetterDomain;
import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import com.h154.saesumMBTI.Enum.LetterState;
import com.h154.saesumMBTI.Enum.LetterType;
import com.h154.saesumMBTI.Response.BadRequestResponse;
import com.h154.saesumMBTI.Response.InternalServerErrorResponse;
import com.h154.saesumMBTI.Response.OKResponseLetterDTO;
import com.h154.saesumMBTI.Response.OkResponse;
import com.h154.saesumMBTI.Response.SurveyResponse.OKResponseQuestionDTO;
import com.h154.saesumMBTI.Service.LetterService;
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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/api/")
public class LetterController {

    private final LetterService letterService;

    @Operation(summary = "편지 생성 요청", description = "편지 생성 합니다.", tags = { "Letter Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseLetterDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/letter/new,", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createLetter(
            @Valid LetterForm letterForm,
            BindingResult result
    ){

        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

        try {
            LetterDomain letterDomain = new LetterDomain();
            letterDomain.setLetterState(LetterState.WRITING);
            letterDomain.setLetterType(LetterType.FOR_ME);
            letterDomain.setLetterContents(letterForm.getLetterContents());

            letterService.joinLetter(letterDomain);

            OKResponseLetterDTO okResponse = new OKResponseLetterDTO("질문 생성 성공", new LetterDTO(letterDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("질문 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "편지 삭제 요청", description = "편지 삭제 합니다.", tags = { "Letter Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/result/{id}/remove")
    public ResponseEntity removeLetter(@PathVariable("id") Long id){
        try {
            letterService.removeLetter(id);
            OkResponse okResponse = new OkResponse("편지 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("편지 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "편지 내용 수정 요청", description = "편지 내용 수정 합니다.", tags = { "Letter Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseLetterDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PatchMapping("/letter/{id}/update")
    public ResponseEntity updateLetterContents(
            @PathVariable("id") Long id,
            @RequestBody LetterBody letterBody
    ){
        try {
            letterService.updateLetterContents(id, letterBody.getLetterContents());
            OkResponse okResponse = new OkResponse("편지 내용 수정 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("편지 내용 수정 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    @Operation(summary = "편지 작성 완료 요청", description = "편지 작성 완료 합니다.", tags = { "Letter Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseLetterDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PatchMapping("/letter/{id}/finish")
    public ResponseEntity finishLetterContents(
            @PathVariable("id") Long id,
            @RequestBody LetterBody letterBody
    ){
        try {
            letterService.finishLetterContents(id, letterBody.getLetterContents());
            OkResponse okResponse = new OkResponse("편지 작성 완료 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("편지 작성 완료 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "편지 조회 요청", description = "편지 조회 합니다.", tags = { "Letter Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseLetterDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/letter/{id}/find")
            public ResponseEntity findLetterContents(
            @PathVariable("id") Long id
    ){
        try{
            letterService.findLetter(id);
            OkResponse okResponse=new OkResponse("편지 조회 완료 성공");
            return new ResponseEntity<>(okResponse, okResponse.getHttpStatus());

        }catch(Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("편지 조회 완료 실패: " + e.getMessage());
            return new ResponseEntity<>(response, response.getHttpStatus());
        }
    }


}


