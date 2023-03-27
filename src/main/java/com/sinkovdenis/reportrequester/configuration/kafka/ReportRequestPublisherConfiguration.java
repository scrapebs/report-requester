package com.sinkovdenis.reportrequester.configuration.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Validated
@Configuration
@ConfigurationProperties(prefix = "spring.kafka.producer")
public class ReportRequestPublisherConfiguration {

    @NotBlank
    private String topic;
    @NotBlank
    private String senderId;
    @NotBlank
    private String senderName;
}
