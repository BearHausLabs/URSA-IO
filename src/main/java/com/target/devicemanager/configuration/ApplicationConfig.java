package com.target.devicemanager.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.target.devicemanager.common.StructuredEventLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class ApplicationConfig {
    private final boolean isSimulationMode;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);
    private static final StructuredEventLogger log = StructuredEventLogger.of(StructuredEventLogger.getConfigurationServiceName(), "ApplicationConfig", LOGGER);

    public ApplicationConfig() {
        isSimulationMode = Boolean.parseBoolean(System.getProperty("useSimulators"));
        log.success("Application Version - PACKAGE_VERSION", 5);
        log.success("Is Simulation Mode - " + isSimulationMode, 5);
    }

    @Bean
    public WebMvcConfigurer configureCors() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String[] fromEnv = getCORSOrigins();
                var mapping = registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
                        .allowedHeaders("*"); //set global CORS policy
                if (fromEnv != null && fromEnv.length > 0) {
                    mapping.allowedOrigins(fromEnv);
                } else {
                    // No CORS_ORIGINS env var set — allow all origins so Angular POS served
                    // from a central dev server can reach the local JavaPOS service on any register.
                    mapping.allowedOriginPatterns("*");
                }
            }
        };
    }

    @Bean
    public Caffeine caffeineConfig() {
        // Keeping cache for 1 hr
        return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
    }

    @Bean
    public CacheManager cacheManager(Caffeine caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }

    private String[] getCORSOrigins() {
        String origins = System.getenv("CORS_ORIGINS");
        return origins != null ? origins.split("\\,") : null;
    }

    public boolean IsSimulationMode() {
        return isSimulationMode;
    }

}

