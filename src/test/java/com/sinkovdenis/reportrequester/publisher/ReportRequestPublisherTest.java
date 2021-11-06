package com.sinkovdenis.reportrequester.publisher;

import com.sinkovdenis.reportrequester.configuration.kafka.KafkaAdditionalHeaders;
import com.sinkovdenis.reportrequester.configuration.properties.RequestPublisherProperties;
import com.sinkovdenis.reportrequester.model.GenericReportRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestPublisherTest {

    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Mock
    private RequestPublisherProperties properties;

    @Mock
    private GenericReportRequest reportRequest;

    @Mock
    private Message<GenericReportRequest> message;

    @Spy
    @InjectMocks
    private ReportRequestPublisher publisher;

    @Test
    public void testPublish() {
        publisher.publish(reportRequest);
        verify(kafkaTemplate).executeInTransaction(any());
    }

    @Test
    public void testPublish_null() {
        assertThat(catchThrowableOfType(() -> publisher.publish(null),
                NullPointerException.class)).isNotNull();
    }

    @Test
    public void testBuildMessage() {
        doReturn("name").when(properties).getSenderName();
        doReturn("id").when(properties).getSenderId();
        Message<GenericReportRequest> message = publisher.buildMessage(reportRequest);
        assertThat(message.getHeaders().get(KafkaAdditionalHeaders.SENDER_NAME)).isEqualTo("name");
        assertThat(message.getHeaders().get(KafkaAdditionalHeaders.SENDER_ID)).isEqualTo("id");
        assertThat(message.getHeaders().get(KafkaAdditionalHeaders.MESSAGE_ID).toString()).isNotEmpty();
    }

    @Test
    public void testBuildMessage_null() {
        assertThat(catchThrowableOfType(() -> publisher.buildMessage(null),
                NullPointerException.class)).isNotNull();
    }
}
