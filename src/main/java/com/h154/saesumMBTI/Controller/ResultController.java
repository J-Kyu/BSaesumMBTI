package com.h154.saesumMBTI.Controller;


import com.h154.saesumMBTI.Controller.Form.*;
import com.h154.saesumMBTI.DTO.Result.*;
import com.h154.saesumMBTI.Domain.Result.*;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Enum.ResultRecordState;
import com.h154.saesumMBTI.Response.BasicResponse;
import com.h154.saesumMBTI.Service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Binding;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Collections;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;



    //result
    @GetMapping("/result/{id}/find")
    public ResponseEntity<BasicResponse> findResult(@PathVariable("id") Long id){
        BasicResponse response = new BasicResponse();

        try{

            ResultDTO resultDTO = resultService.findResult(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("결과 생성 성공")
                    .result(Arrays.asList(resultDTO))
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("결과 조회 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @PostMapping("/result/{id}/remove")
    public  ResponseEntity<BasicResponse> removeResult(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try{

            resultService.removeResult(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("결과 삭제 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(400)
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .message("결과 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }



    //PROS CRUD
    //Create
    @PostMapping("/result/pros/new")
    public ResponseEntity<BasicResponse> createResultPros(@Valid ResultProsForm form, BindingResult result){
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

            ResultProsDomain prosDomain = new ResultProsDomain();
            prosDomain.SetDomainData(form);
            resultService.joinResultPros(prosDomain);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 생성 성공")
                    .result(Collections.emptyList())
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 생성 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }



        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @GetMapping("/result/pros/{id}/find")
    public ResponseEntity<BasicResponse> findResultPros(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try {

            ResultProsDTO resultProsDTO = resultService.findResultPros(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 조회 성공")
                    .result(Arrays.asList(resultProsDTO))
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("장점 조회 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response,response.getHttpStatus());

    }

    @PostMapping("/result/pros/{id}/remove")
    public  ResponseEntity<BasicResponse> removeResultPros(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try{

            resultService.removeResultPros(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 삭제 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("단점 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/result/pros/{id}/update")
    public  ResponseEntity<BasicResponse> updateResultPros(@PathVariable("id") Long id,@Valid ResultProsForm form, BindingResult result){

        BasicResponse response = new BasicResponse();

        try{

            resultService.updateResultProsContents(id, form.getProsContents());

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 수정 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("장점 수정 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }




    // CONS CRUD
    @PostMapping("/result/cons/new")
    public ResponseEntity<BasicResponse> createResultCons(@Valid ResultConsForm form, BindingResult result){

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

            ResultConsDomain resultConsDomain = new ResultConsDomain();
            resultConsDomain.SetDomainData(form);
            resultService.joinResultCons(resultConsDomain);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 생성 성공")
                    .result(Collections.emptyList())
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 생성 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @GetMapping("/result/cons/{id}/find")
    public ResponseEntity<BasicResponse> findResultCons(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try {

            ResultConsDTO resultConsDTO = resultService.findResultCons(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 조회 성공")
                    .result(Arrays.asList(resultConsDTO))
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("단점 조회 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response,response.getHttpStatus());

    }

    @PostMapping("/result/cons/{id}/remove")
    public  ResponseEntity<BasicResponse> removeResultCons(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try{

            resultService.removeResultCons(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 삭제 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("단점 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/result/cons/{id}/update")
    public  ResponseEntity<BasicResponse> updateResultCons(@PathVariable("id") Long id,@Valid ResultConsForm form, BindingResult result){

        BasicResponse response = new BasicResponse();

        try{

            resultService.updateResultConsContents(id, form.getConsContents());

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 수정 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("단점 수정 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }




    // Situation CRUD
    @PostMapping("/result/situation/new")
    public ResponseEntity<BasicResponse> createProsSituation(@Valid ProsSituationForm form, BindingResult result){

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

            ProsSituationDomain situationDomain = new ProsSituationDomain();
            situationDomain.SetDomainData(form);
            resultService.joinProsSituation(situationDomain);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 상황 생성 성공")
                    .result(Collections.emptyList())
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 상황 생성 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @GetMapping("/result/situation/{id}/find")
    public ResponseEntity<BasicResponse> findProsSituation(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try {

            ProsSituationDTO prosSituationDTO = resultService.findProsSituation(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 상황 조회 성공")
                    .result(Arrays.asList(prosSituationDTO))
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("장점 상황 조회 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response,response.getHttpStatus());

    }

    @PostMapping("/result/situation/{id}/remove")
    public  ResponseEntity<BasicResponse> removeProsSituation(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try{

            resultService.removeProsSituation(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 상황 삭제 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("장점 상황 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/result/situation/{id}/update")
    public  ResponseEntity<BasicResponse> updateProsSituation(@PathVariable("id") Long id,@Valid ProsSituationDTO form, BindingResult result){

        BasicResponse response = new BasicResponse();

        try{

            resultService.updateProsSituation(id, form.getSituationContents());

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 상황 수정 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("장점 상황 수정 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }



    // Situation CRUD
    @PostMapping("/result/tip/new")
    public ResponseEntity<BasicResponse> createConsTip(@Valid ConsTipForm form, BindingResult result){

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

            ConsTipDomain consTipDomain = new ConsTipDomain();
            consTipDomain.SetDomainData(form);
            resultService.joinConsTip(consTipDomain);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 Tip 생성 성공")
                    .result(Collections.emptyList())
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 Tip 생성 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());

    }

    @GetMapping("/result/tip/{id}/find")
    public ResponseEntity<BasicResponse> findConsTip(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try {

            ConsTipDTO consTipDTO = resultService.findConsTip(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 Tip 조회 성공")
                    .result(Arrays.asList(consTipDTO))
                    .build();
        }
        catch (Exception e){

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 Tip 조회 실패."+e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response,response.getHttpStatus());

    }

    @PostMapping("/result/tip/{id}/remove")
    public  ResponseEntity<BasicResponse> removeConsTip(@PathVariable("id") Long id){

        BasicResponse response = new BasicResponse();

        try{

            resultService.removeConsTip(id);

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("단점 Tip 삭제 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("단점 Tip 삭제 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }

    @PostMapping("/result/tip/{id}/update")
    public  ResponseEntity<BasicResponse> updateConsTip(@PathVariable("id") Long id,@Valid ConsTipForm form, BindingResult result){

        BasicResponse response = new BasicResponse();

        try{

            resultService.updateConsTip(id, form.getTipContents());

            response = BasicResponse.builder()
                    .code(200)
                    .httpStatus(HttpStatus.OK)
                    .message("장점 상황 수정 성공")
                    .result(Collections.emptyList())
                    .build();

        }
        catch (Exception e){
            response = BasicResponse.builder()
                    .code(500)
                    .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("장점 상황 수정 실패." + e.getMessage())
                    .result(Collections.emptyList())
                    .build();
        }


        return new ResponseEntity<>(response, response.getHttpStatus());
    }


    //selected result <--> pros


    //selected result <--> cons


    // selected pros <--> situation

    // selected cons <--> tip

    // selected result  <--> hash tag


}
