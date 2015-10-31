/* Spring Boot Example http://spring.io/guides/gs/spring-boot/
 * 
 * Run in IDE as java application or shell > run.sh 
 * Open localhost:8080
 * Spring Boot Actuator endpoints:
 * http://localhost:8080/health
 * http://localhost:8080/metrics and etc.
 */

package com.example;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackageClasses = {HelloController.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Autowired
    private TypeResolver typeResolver;

    
    @Bean
    public Docket petApi() {
      return new Docket(DocumentationType.SWAGGER_2)
          .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
          .pathMapping("/")
          .directModelSubstitute(LocalDate.class, String.class)
          .genericModelSubstitutes(ResponseEntity.class)
          .alternateTypeRules(
              newRule(typeResolver.resolve(DeferredResult.class,
                      typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                      typeResolver.resolve(WildcardType.class)))
          .useDefaultResponseMessages(false)
          // override the 500 error code for all GETs
          .globalResponseMessage(RequestMethod.GET,
              newArrayList(new ResponseMessageBuilder()
                  .code(500)
                  .message("500 message")
                  .responseModel(new ModelRef("Error"))
                  .build()));
    }

}