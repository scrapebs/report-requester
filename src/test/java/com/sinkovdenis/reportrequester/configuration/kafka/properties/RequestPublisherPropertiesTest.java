package com.sinkovdenis.reportrequester.configuration.kafka.properties;

import com.sinkovdenis.reportrequester.GenericTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class RequestPublisherPropertiesTest extends GenericTest {
    
    @Autowired
    private RequestPublisherProperties properties;
    
    @Test
    void testProperties() {
        assertThat(properties.getTopic()).isNotBlank();
        assertThat(properties.getSenderId()).isNotBlank();
        assertThat(properties.getSenderName()).isNotBlank();
    }
    
}
