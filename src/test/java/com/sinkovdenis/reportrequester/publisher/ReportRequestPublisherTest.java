package com.sinkovdenis.reportrequester.publisher;

import com.sinkovdenis.reportrequester.configuration.kafka.KafkaAdditionalHeaders;
import com.sinkovdenis.reportrequester.configuration.kafka.ReportRequestPublisherConfiguration;
import com.sinkovdenis.reportrequester.model.request.GenericReportRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportRequestPublisherTest {

    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Mock
    private ReportRequestPublisherConfiguration configuration;

    @Mock
    private GenericReportRequest reportRequest;
    
    @Spy
    @InjectMocks
    private ReportRequestPublisher publisher;

    @Test
    void testPublish() {
        publisher.publish(reportRequest);
        verify(kafkaTemplate).executeInTransaction(any());
    }

    @Test
    void testPublish_null() {
        assertThat(catchThrowableOfType(() -> publisher.publish(null),
                NullPointerException.class)).isNotNull();
    }

    @Test
    void testBuildMessage() {
        Message<GenericReportRequest> message = publisher.buildMessage(reportRequest);
        assertThat(message.getHeaders().get(KafkaAdditionalHeaders.MESSAGE_ID).toString()).isNotEmpty();
    }

    @Test
    void testBuildMessage_null() {
        assertThat(catchThrowableOfType(() -> publisher.buildMessage(null),
                NullPointerException.class)).isNotNull();
    }
}
