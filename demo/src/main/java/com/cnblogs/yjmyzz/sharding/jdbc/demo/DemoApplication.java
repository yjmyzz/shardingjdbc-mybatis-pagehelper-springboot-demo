package com.cnblogs.yjmyzz.sharding.jdbc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 菩提树下的杨过
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.cnblogs.yjmyzz.sharding.jdbc.demo.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
