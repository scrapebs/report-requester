package com.sinkovdenis.reportrequester.persistence.repo;

import com.sinkovdenis.reportrequester.GenericTest;
import com.sinkovdenis.reportrequester.model.ReportType;
import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.sinkovdenis.reportrequester.TestData.createAcceptedRequestEntity;
import static org.assertj.core.api.Assertions.assertThat;

public class AcceptedRequestRepositoryTest extends GenericTest {
    
    @Autowired
    private AcceptedRequestRepository repository;
    
    @Before
    public void setUp() {
        repository.deleteAll();
    }
    
    @Test
    public void testSave_and_findBy() {
        AcceptedRequestEntity savedAcceptedRequest = repository.save(createAcceptedRequestEntity());
        assertThat(savedAcceptedRequest).isNotNull();
        
        AcceptedRequestEntity foundAcceptedRequest = repository.findById(savedAcceptedRequest.getRequestId()).get();
        assertThat(foundAcceptedRequest).isNotNull();
        assertThat(savedAcceptedRequest).isEqualTo(foundAcceptedRequest);
    }

    @Test
    public void testFindAll() {
        repository.save(createAcceptedRequestEntity());
        repository.save(createAcceptedRequestEntity());

        assertThat(repository.findAll())
                .isNotEmpty()
                .hasSize(2);
    }
}
