package com.mindata.app.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
@Data
public class ApplicationProperties {

    private final Server server = new Server();

    @Data
    public static class Server {
        private String host;
        private String port;
    }
}
