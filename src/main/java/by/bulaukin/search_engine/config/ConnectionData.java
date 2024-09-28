package by.bulaukin.search_engine.config;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "connection-settings")
public class ConnectionData {

    private String userAgent;
    private String referrer;
}
