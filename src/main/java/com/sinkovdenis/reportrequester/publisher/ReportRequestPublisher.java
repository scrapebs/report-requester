package com.sinkovdenis.reportrequester.publisher;

import com.sinkovdenis.reportrequester.configuration.kafka.KafkaAdditionalHeaders;
import com.sinkovdenis.reportrequester.configuration.properties.RequestPublisherConfiguration;
import com.sinkovdenis.reportrequester.model.GenericReportRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReportRequestPublisher {

    private final KafkaTemplate<Object, Object> kafkaTemplate;
    private final RequestPublisherConfiguration configuration;

    public void publish(@NonNull GenericReportRequest reportRequest) {
        kafkaTemplate.send(buildMessage(reportRequest));
        this.kafkaTemplate.flush();
    }

    Message<GenericReportRequest> buildMessage(@NonNull GenericReportRequest event) {
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, configuration.getTopic())
                .setHeader(KafkaAdditionalHeaders.SENDER_ID, configuration.getSenderId())
                .setHeader(KafkaAdditionalHeaders.SENDER_NAME, configuration.getSenderName())
                .setHeader(KafkaAdditionalHeaders.MESSAGE_ID, UUID.randomUUID().toString())
                .build();
    }
}
