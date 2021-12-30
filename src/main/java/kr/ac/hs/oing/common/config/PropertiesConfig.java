package kr.ac.hs.oing.common.config;

import kr.ac.hs.oing.common.property.CorsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class PropertiesConfig {

}