package com.h154.saesumMBTI.Controller;

import com.h154.saesumMBTI.Controller.Form.Test;
import com.h154.saesumMBTI.Response.BadRequestResponse;
import com.h154.saesumMBTI.Response.InternalServerErrorResponse;
import com.h154.saesumMBTI.Response.OkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(){
        log.info("Home Controller");
        return "<h1> this is home</h1>";
    }


    @Operation(summary = "Test", description = "Test", tags = { "Home Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = OkResponse.class))),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content(schema = @Schema(implementation = BadRequestResponse.class))),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR",  content = @Content(schema = @Schema(implementation = InternalServerErrorResponse.class)))
    })

    @PostMapping("/test")
    public ResponseEntity test(
            @RequestBody() Test test
    ) {


        try {
            log.info(test.getTitle());
            log.info(test.getValues().toString());

            OkResponse okResponse = new OkResponse("Test 성공");
            return new ResponseEntity<>(okResponse,okResponse.getHttpStatus());

        } catch (Exception e) {
            InternalServerErrorResponse response = new InternalServerErrorResponse("Test 실패: "+e.getMessage());
            return new ResponseEntity<>(response,response.getHttpStatus());
        }
    }
}
