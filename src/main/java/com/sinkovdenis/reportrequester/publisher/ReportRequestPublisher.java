package com.sinkovdenis.reportrequester.publisher;

import com.sinkovdenis.reportrequester.configuration.kafka.KafkaAdditionalHeaders;
import com.sinkovdenis.reportrequester.configuration.kafka.ReportRequestPublisherConfiguration;
import com.sinkovdenis.reportrequester.model.request.GenericReportRequest;
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
    private final ReportRequestPublisherConfiguration configuration;

    public <R extends GenericReportRequest> void publish(@NonNull R reportRequest) {
        kafkaTemplate.executeInTransaction(operation -> operation.send(buildMessage(reportRequest)));
    //    kafkaTemplate.send(buildMessage(reportRequest));
    }

    <R extends GenericReportRequest> Message<R> buildMessage(@NonNull R event) {
        return MessageBuilder.withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, configuration.getTopic())
                .setHeader(KafkaAdditionalHeaders.MESSAGE_ID, UUID.randomUUID().toString())
                .build();
    }
}
