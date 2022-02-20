package com.sinkovdenis.reportrequester.persistence.repo;

import com.sinkovdenis.reportrequester.persistence.entity.AcceptedRequestEntity;
import org.springframework.data.repository.CrudRepository;

public interface AcceptedRequestRepository extends CrudRepository<AcceptedRequestEntity, Long> {
    
}
