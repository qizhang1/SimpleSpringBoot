package com.example;

import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.*;


// combines @Controller and @ResponseBody
@RestController
@RequestMapping(value="/v1/")
@Api(value = "/hello")
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET )
    @ApiOperation(value = "value", notes = "notes", response = String.class)
    @ApiResponses(value = {
    		@ApiResponse(code = 201, message = "OK")})
    public String home() {
    	return "Hello Spring Boot!";
    }
}
