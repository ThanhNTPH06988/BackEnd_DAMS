package com.tamdt.pmbackend;

import com.tamdt.pmbackend.config.fileConfig.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class PmbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PmbackendApplication.class, args);
    }

}
