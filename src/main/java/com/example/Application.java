/* Spring Boot Example http://spring.io/guides/gs/spring-boot/
 * Springfox http://springfox.github.io/springfox/docs/current/ 
 * 
 * Run in IDE as java application or shell > run.sh 
 * Open localhost:8080
 * Spring Boot Actuator endpoints:
 * http://localhost:8080/health
 * http://localhost:8080/metrics and etc.
 * http://localhost:8080/swagger-ui.html
 */

package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.*;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {HelloController.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public Docket petApi() {
      return new Docket(DocumentationType.SWAGGER_2)
    	  .apiInfo(apiInfo())
//          .select()
//            .apis(RequestHandlerSelectors.basePackage("com.example"))
//            .build();
//     Globally overriding response messages for different http methods
      .useDefaultResponseMessages(false)
      .globalResponseMessage(RequestMethod.GET, 
    		  newArrayList(new ResponseMessageBuilder()
    				  .code(500).message("Internal server error")
    				  .responseModel(new ModelRef("Error"))
    				  .build()));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Hello Swagger API")
                .description("Swagger Example")
                .termsOfServiceUrl("http://springfox.io")
                .contact("qi.zhang@concur.com")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

}