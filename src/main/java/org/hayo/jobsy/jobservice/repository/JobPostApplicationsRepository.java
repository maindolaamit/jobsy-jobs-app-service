package org.hayo.jobsy.jobservice.repository;

import org.hayo.jobsy.jobservice.models.entity.JobPostApplicationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostApplicationsRepository extends MongoRepository<JobPostApplicationEntity, String> {
    List<JobPostApplicationEntity> findAllByJobPostId(String jobPostId);

}
