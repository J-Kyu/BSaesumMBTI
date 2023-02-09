package com.h154.saesumMBTI.Controller;


import com.h154.saesumMBTI.Controller.Form.*;
import com.h154.saesumMBTI.DTO.Result.*;
import com.h154.saesumMBTI.Domain.Result.*;
import com.h154.saesumMBTI.Response.BadRequestResponse;
import com.h154.saesumMBTI.Response.InternalServerErrorResponse;
import com.h154.saesumMBTI.Response.OkResponse;
import com.h154.saesumMBTI.Response.ResultResponse.*;
import com.h154.saesumMBTI.Service.ResultService;
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
@RequestMapping("/v1/admin/api")
public class ResultController {

    private final ResultService resultService;



    //result
    @Operation(summary = "결과 조회 요청", description = "결과 값을 조회 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseResultDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/result/{id}/find")
    public ResponseEntity findResult(@PathVariable("id") Long id){

        try{

            ResultDTO resultDTO = resultService.findResult(id);
            OKResponseResultDTO okResponse = new OKResponseResultDTO("결과 조회 성공",resultDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());


        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("결과 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }


    @Operation(summary = "결과 삭제 요청", description = "결과 값을 삭제 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/result/{id}/remove")
    public  ResponseEntity removeResult(@PathVariable("id") Long id){


        try{
            resultService.removeResult(id);
            OkResponse okResponse = new OkResponse("결과 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("결과 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }



    //PROS CRUD
    //Create
    @Operation(summary = "장점 생성 요청", description = "장점 생성 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseProsDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/result/pros/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createResultPros(@Valid ResultProsForm form, BindingResult result){

        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }


        try {

            ResultProsDomain prosDomain = new ResultProsDomain();
            prosDomain.SetDomainData(form);
            resultService.joinResultPros(prosDomain);
            OKResponseProsDTO okResponse = new OKResponseProsDTO("장점 생성 성공",new ResultProsDTO(prosDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("장점 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "장점 조회 요청", description = "장점 조회 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseProsDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/result/pros/{id}/find")
    public ResponseEntity findResultPros(@PathVariable("id") Long id){

        try {

            ResultProsDTO resultProsDTO = resultService.findResultPros(id);
            OKResponseProsDTO okResponse = new OKResponseProsDTO("장점 조회 성공",resultProsDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("장점 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }

    @Operation(summary = "장점 삭제 요청", description = "장점 삭제 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/result/pros/{id}/remove")
    public  ResponseEntity removeResultPros(@PathVariable("id") Long id){

        try{
            resultService.removeResultPros(id);

            OkResponse okResponse = new OkResponse("장점 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){

            InternalServerErrorResponse response = new InternalServerErrorResponse("장점 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }

    @Operation(summary = "장점 수정 요청", description = "장점 수정 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseProsDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PutMapping(value = "/result/pros/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity updateResultPros(
            @PathVariable("id") Long id,
            @Valid ResultProsForm form,
            BindingResult result
    ){

        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }



        try{

            ResultProsDTO resultProsDTO = resultService.updateResultProsContents(id, form.getProsContents());
            OKResponseProsDTO okResponseProsDTO = new OKResponseProsDTO("장점 수정 성공", resultProsDTO);
            return new ResponseEntity<>(okResponseProsDTO,okResponseProsDTO.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("장점 수정 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }




    // CONS CRUD
    @Operation(summary = "단점 생성 요청", description = "단점 생성 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseConsDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/result/cons/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createResultCons(
            @Valid ResultConsForm form,
            BindingResult result
    ){


        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }


        try {

            ResultConsDomain resultConsDomain = new ResultConsDomain();
            resultConsDomain.SetDomainData(form);
            resultService.joinResultCons(resultConsDomain);

            OKResponseConsDTO okResponse = new OKResponseConsDTO("단점 생성 성공", new ResultConsDTO(resultConsDomain));
            return new ResponseEntity<>(okResponse, okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());

        }
    }

    @Operation(summary = "단점 조회 요청", description = "단점 조회 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseConsDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/result/cons/{id}/find")
    public ResponseEntity findResultCons(@PathVariable("id") Long id){


        try {

            ResultConsDTO resultConsDTO = resultService.findResultCons(id);

            OKResponseConsDTO okResponse = new OKResponseConsDTO("단점 생성 성공", resultConsDTO);
            return new ResponseEntity<>(okResponse, okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "단점 삭제 요청", description = "단점 삭제 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/result/cons/{id}/remove")
    public  ResponseEntity removeResultCons(@PathVariable("id") Long id){


        try{

            resultService.removeResultCons(id);


            OkResponse okResponse = new OkResponse("단점 생성 성공");
            return new ResponseEntity<>(okResponse, okResponse.getHttpStatus());


        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "단점 수정 요청", description = "단점 수정 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PutMapping(value = "/result/cons/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity updateResultCons(@PathVariable("id") Long id,@Valid ResultConsForm form, BindingResult result){

        try{
            resultService.updateResultConsContents(id, form.getConsContents());

            OkResponse okResponse = new OkResponse("단점 수정 성공");
            return new ResponseEntity<>(okResponse, okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 수정 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }




    // Situation CRUD
    @Operation(summary = "장점 상황 생성 요청", description = "장점 상황 생성 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseSituationDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PostMapping(value = "/result/situation/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProsSituation(
            @Valid ProsSituationForm form,
            BindingResult result
    ){


        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }


        try {

            ProsSituationDomain situationDomain = new ProsSituationDomain();
            situationDomain.SetDomainData(form);
            resultService.joinProsSituation(situationDomain);

            OKResponseSituationDTO okResponse = new OKResponseSituationDTO("장점 상황 생성 성공", new ProsSituationDTO(situationDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("장점 상황 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    @Operation(summary = "장점 상황 조회 요청", description = "장점 상황 조회 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseSituationDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/result/situation/{id}/find")
    public ResponseEntity findProsSituation(@PathVariable("id") Long id){


        try {

            ProsSituationDTO prosSituationDTO = resultService.findProsSituation(id);

            OKResponseSituationDTO okResponse = new OKResponseSituationDTO("장점 상황 조회 성공", prosSituationDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("장점 상황 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    @Operation(summary = "장점 상황 삭제 요청", description = "장점 상황 삭제 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/result/situation/{id}/remove")
    public  ResponseEntity removeProsSituation(@PathVariable("id") Long id){

        try{

            resultService.removeProsSituation(id);

            OkResponse okResponse = new OkResponse("장점 상황 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){

            InternalServerErrorResponse response = new InternalServerErrorResponse("장점 상황 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

    }


    @Operation(summary = "장점 상황 수정 요청", description = "장점 상황 수정 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseSituationDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PutMapping(value = "/result/situation/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity updateProsSituation(
            @PathVariable("id") Long id,
            @Valid ProsSituationDTO form,
            BindingResult result){

        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }

        try{

            ProsSituationDTO prosSituationDTO = resultService.updateProsSituation(id, form.getSituationContents());
            OKResponseSituationDTO okResponse = new OKResponseSituationDTO("회원 생성 성공", prosSituationDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());


        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("회원 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    /*** 단점 TIP ***/

    @Operation(summary = "단점 Tip 생성 요청", description = "단점 Tip 생성 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseTipDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    // Situation CRUD
    @PostMapping(value = "/result/tip/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createConsTip(
            @Valid ConsTipForm form,
            BindingResult result
    ){


        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }


        try {

            ConsTipDomain consTipDomain = new ConsTipDomain();
            consTipDomain.SetDomainData(form);
            resultService.joinConsTip(consTipDomain);

            OKResponseTipDTO okResponse = new OKResponseTipDTO("단점 Tip 생성 성공", new ConsTipDTO(consTipDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){

            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 Tip 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());

        }
    }


    @Operation(summary = "단점 Tip 조회 요청", description = "단점 Tip 조회 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseTipDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/result/tip/{id}/find")
    public ResponseEntity findConsTip(@PathVariable("id") Long id){

        try {

            ConsTipDTO consTipDTO = resultService.findConsTip(id);
            OKResponseTipDTO okResponse = new OKResponseTipDTO("단점 Tip 조회 성공", consTipDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 Tip 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "단점 Tip 삭제 요청", description = "단점 Tip 삭제 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/result/tip/{id}/remove")
    public  ResponseEntity removeConsTip(@PathVariable("id") Long id){

        try{
            resultService.removeConsTip(id);
            OkResponse okResponse = new OkResponse("단점 Tip 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 Tip 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    @Operation(summary = "단점 Tip  수정", description = "단점 Tip 수정 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PutMapping(value = "/result/tip/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity updateConsTip(
            @PathVariable("id") Long id,
            @Valid ConsTipForm form,
            BindingResult result){


        try{
            resultService.updateConsTip(id, form.getTipContents());

            OkResponse okResponse = new OkResponse("단점 Tip 수정 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("단점 Tip 수정 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    /*** Hash Tag ***/

    @Operation(summary = " Hash Tag 생성 요청", description = "Hash Tag 생성 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseHashTagDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    // Situation CRUD
    @PostMapping(value = "/result/hashTag/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createHashTag(
            @Valid HashTagForm form,
            BindingResult result
    ){


        if (result.hasErrors()){
            BadRequestResponse response = new BadRequestResponse("Input Form 오류");
            return new ResponseEntity<>(response,response.getHttpStatus());
        }


        try {
            HashTagDomain hashTagDomain = new HashTagDomain();
            hashTagDomain.SetDomainData(form);


            resultService.joinHashTag(hashTagDomain);

            OKResponseHashTagDTO okResponse = new OKResponseHashTagDTO("Hash Tag 생성 성공", new HashTagDTO(hashTagDomain));
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        }
        catch (Exception e){

            InternalServerErrorResponse response = new InternalServerErrorResponse("Hash Tag 생성 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());

        }
    }


    @Operation(summary = "Hash Tag 조회 요청", description = "Hash Tag  조회 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseHashTagDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @GetMapping("/result/hashTag/{id}/find")
    public ResponseEntity findHashTag(
            @PathVariable("id") Long id
    ){

        try {
            HashTagDTO hashTagDTO = resultService.findHashTag(id);
            OKResponseHashTagDTO okResponse = new OKResponseHashTagDTO("Hash Tag 조회 성공", hashTagDTO);
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("Hash Tag 조회 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }


    @Operation(summary = "Hash Tag 삭제 요청", description = "Hahs Tag 삭제 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @DeleteMapping("/result/hashTag/{id}/remove")
    public  ResponseEntity removeHashTag(@PathVariable("id") Long id){

        try{
            resultService.removeConsTip(id);
            OkResponse okResponse = new OkResponse("Hash Tag 삭제 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("Hash Tag 삭제 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }

    @Operation(summary = "Hash Tag 수정", description = "Hash Tag 수정 합니다.", tags = { "Result Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OKResponseHashTagDTO.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })
    @PutMapping(value = "/result/hashTag/{id}/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity updateHashTag(
            @PathVariable("id") Long id,
            @Valid HashTagForm form,
            BindingResult result){


        try{
            resultService.updateHashTag(id, form.getHashTagContents());

            OKResponseHashTagDTO okResponse = new OKResponseHashTagDTO("Hash Tag 수정 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());
        }
        catch (Exception e){
            InternalServerErrorResponse response = new InternalServerErrorResponse("Hash Tag 수정 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }
    //selected result <--> pros


    //selected result <--> cons


    // selected pros <--> situation

    // selected cons <--> tip

    // selected result  <--> hash tag


}
