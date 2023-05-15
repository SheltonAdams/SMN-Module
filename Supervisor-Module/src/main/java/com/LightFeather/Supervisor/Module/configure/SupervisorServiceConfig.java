package com.LightFeather.Supervisor.Module.configure;

import com.LightFeather.Supervisor.Module.service.SupervisorService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SupervisorServiceConfig {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    public SupervisorService supervisorService(){
        return new SupervisorServiceImpl(new RestTemplate());
    }
}
