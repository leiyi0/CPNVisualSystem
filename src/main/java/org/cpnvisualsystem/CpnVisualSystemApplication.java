package org.cpnvisualsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.cpnvisualsystem.mapper")
public class CpnVisualSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CpnVisualSystemApplication.class, args);
    }

}
