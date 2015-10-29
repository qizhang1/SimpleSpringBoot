/* Spring Boot Example http://spring.io/guides/gs/spring-boot/
 * 
 * Run in IDE as java application or shell > run.sh 
 * Open localhost:8080
 * Spring Boot Actuator endpoints:
 * http://localhost:8080/health
 * http://localhost:8080/metrics and etc.
 */

package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}