package com.sinkovdenis.reportrequester.persistence.repo;

import com.sinkovdenis.reportrequester.GenericTestWithoutKafka;
import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.sinkovdenis.reportrequester.TestData.createAcceptedRequestEntity;
import static org.assertj.core.api.Assertions.assertThat;

class AcceptedRequestRepositoryTest extends GenericTestWithoutKafka {
    
    @Autowired
    private AcceptedRequestRepository repository;
    
    @BeforeEach
    public void setUp() {
        repository.deleteAll();
    }
    
    @Test
    void testSave_and_findBy() {
        AcceptedRequestEntity savedAcceptedRequest = repository.save(createAcceptedRequestEntity());
        assertThat(savedAcceptedRequest).isNotNull();
        
        AcceptedRequestEntity foundAcceptedRequest = repository.findById(savedAcceptedRequest.getRequestId()).get();
        assertThat(foundAcceptedRequest).isNotNull();
        assertThat(savedAcceptedRequest).isEqualTo(foundAcceptedRequest);
    }

    @Test
    void testFindAll() {
        repository.save(createAcceptedRequestEntity());
        repository.save(createAcceptedRequestEntity());

        assertThat(repository.findAll())
                .isNotEmpty()
                .hasSize(2);
    }
}
