package com.sinkovdenis.reportrequester.configuration.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@ToString
@Validated
@Configuration
@ConfigurationProperties(prefix = "spring.kafka.producer")
public class ReportRequestPublisherConfiguration {

    private String topic;
}
