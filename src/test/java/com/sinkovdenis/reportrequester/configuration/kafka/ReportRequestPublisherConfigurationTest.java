package com.sinkovdenis.reportrequester.configuration.kafka;

import com.sinkovdenis.reportrequester.GenericTestWithoutKafka;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class ReportRequestPublisherConfigurationTest extends GenericTestWithoutKafka {
    
    @Autowired
    private ReportRequestPublisherConfiguration configuration;
    
    @Test
    void testProperties() {
        assertThat(configuration.getTopic()).isNotBlank();
    }
    
}
