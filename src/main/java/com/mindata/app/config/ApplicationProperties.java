package com.mindata.app.config;

import liquibase.pro.packaged.L;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class ApplicationProperties {

    private final Server server = new Server();

    @Data
    public static class Server {
        private String host;
        private String port;
    }
}
