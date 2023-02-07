package com.h154.saesumMBTI.Controller;


import com.h154.saesumMBTI.DTO.Result.ResultDTO;
import com.h154.saesumMBTI.Response.BasicResponse;
import com.h154.saesumMBTI.Service.ResultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;
    @GetMapping("/result/{id}/load")
    public ResponseEntity<BasicResponse> loadResultRecord(@PathVariable("id") Long id){
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


}
