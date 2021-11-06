package com.sinkovdenis.reportrequester.publisher;

import com.sinkovdenis.reportrequester.configuration.kafka.KafkaAdditionalHeaders;
import com.sinkovdenis.reportrequester.configuration.properties.RequestPublisherProperties;
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
    private final RequestPublisherProperties properties;

    public void publish(@NonNull GenericReportRequest reportRequest) {
        kafkaTemplate.executeInTransaction(operation -> operation.send(buildMessage(reportRequest)));
    }

    Message<GenericReportRequest> buildMessage(@NonNull GenericReportRequest event) {
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, properties.getTopic())
                .setHeader(KafkaAdditionalHeaders.SENDER_ID, properties.getSenderId())
                .setHeader(KafkaAdditionalHeaders.SENDER_NAME, properties.getSenderName())
                .setHeader(KafkaAdditionalHeaders.MESSAGE_ID, UUID.randomUUID().toString())
                .build();
    }
}
