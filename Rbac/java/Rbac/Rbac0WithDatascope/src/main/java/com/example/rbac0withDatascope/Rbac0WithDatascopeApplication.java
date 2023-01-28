package com.example.rbac0withDatascope;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

@SpringBootApplication
public class Rbac0WithDatascopeApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Rbac0WithDatascopeApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
    }


}
