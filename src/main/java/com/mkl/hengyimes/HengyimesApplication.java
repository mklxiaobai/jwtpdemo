package com.mkl.hengyimes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wf.jwtp.configuration.EnableJwtPermission;

@SpringBootApplication
@EnableJwtPermission
@MapperScan("com.mkl.hengyimes.user.mapper")
public class HengyimesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HengyimesApplication.class, args);
    }

}
