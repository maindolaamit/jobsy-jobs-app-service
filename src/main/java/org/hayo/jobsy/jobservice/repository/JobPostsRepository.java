package org.hayo.jobsy.jobservice.repository;

import org.hayo.jobsy.jobservice.models.entity.JobPostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostsRepository extends MongoRepository<JobPostEntity, String> {
}
