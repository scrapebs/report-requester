package com.sinkovdenis.reportrequester.publisher;

import com.sinkovdenis.reportrequester.configuration.kafka.KafkaAdditionalHeaders;
import com.sinkovdenis.reportrequester.configuration.properties.RequestPublisherConfiguration;
import com.sinkovdenis.reportrequester.model.ReportRequest;

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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportRequestPublisherTest {

    @Mock
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Mock
    private RequestPublisherConfiguration configuration;

    @Mock
    private ReportRequest reportRequest;

    @Mock
    private Message<ReportRequest> message;

    @Spy
    @InjectMocks
    private ReportRequestPublisher publisher;

    @Test
    public void testPublish() {
        doReturn(message).when(publisher).buildMessage(reportRequest);
        publisher.publish(reportRequest);
        verify(publisher).buildMessage(reportRequest);
        verify(kafkaTemplate).flush();
    }

    @Test
    public void testPublish_null() {
        assertThat(catchThrowableOfType(() -> publisher.publish(null),
                NullPointerException.class)).isNotNull();
    }

    @Test
    public void testBuildMessage() {
        doReturn("name").when(configuration).getSenderName();
        doReturn("id").when(configuration).getSenderId();
        Message<ReportRequest> message = publisher.buildMessage(reportRequest);
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
