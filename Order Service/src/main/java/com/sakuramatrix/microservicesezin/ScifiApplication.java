package com.sakuramatrix.microservicesezin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class ScifiApplication {
        public static void main(String[] args) {
                SpringApplication.run(ScifiApplication.class, args);

        }
}

