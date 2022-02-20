package com.sinkovdenis.reportrequester.configuration.kafka.properties;

import com.sinkovdenis.reportrequester.GenericTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestPublisherPropertiesTest extends GenericTest {
    
    @Autowired
    private RequestPublisherProperties properties;
    
    @Test
    public void testProperties() {
        assertThat(properties.getTopic()).isNotBlank();
        assertThat(properties.getSenderId()).isNotBlank();
        assertThat(properties.getSenderName()).isNotBlank();
    }
    
}
