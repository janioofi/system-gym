package br.janioofi.system_gym;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class SystemGymApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemGymApplication.class, args);
    }

}
