package com.sinkovdenis.reportrequester;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test_without_kafka")
public class TestKafkaOffConfiguration {

    @SuppressWarnings("unchecked")
    @Bean
    @Primary
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return mock(KafkaTemplate.class);
    }
}
