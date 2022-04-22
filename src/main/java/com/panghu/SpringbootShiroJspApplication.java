package com.panghu;

import com.panghu.utils.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author panghuhu
 */
@SpringBootApplication
@MapperScan("com.panghu.dao")
public class SpringbootShiroJspApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootShiroJspApplication.class, args);
		SpringContextUtil.setApplicationContext(context);
	}

}
