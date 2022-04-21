package com.panghu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.panghu.dao")
public class SpringbootShiroJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShiroJspApplication.class, args);
	}

}
