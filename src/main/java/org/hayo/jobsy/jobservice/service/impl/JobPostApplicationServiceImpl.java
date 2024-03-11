package org.hayo.jobsy.jobservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hayo.jobsy.dto.job.JobPostApplication;
import org.hayo.jobsy.dto.job.JobPostApplicationsSearchRequest;
import org.hayo.jobsy.enums.jobs.JobPostApplicationStatus;
import org.hayo.jobsy.jobservice.models.entity.JobPostApplicationEntity;
import org.hayo.jobsy.jobservice.models.exception.JobPostApplicationDoesNotExistsException;
import org.hayo.jobsy.jobservice.models.mapper.JobPostApplicationsMapper;
import org.hayo.jobsy.jobservice.repository.JobPostApplicationsRepository;
import org.hayo.jobsy.jobservice.service.JobPostApplicationService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class JobPostApplicationServiceImpl implements JobPostApplicationService {

    private final JobPostApplicationsRepository jobPostApplicationsRepository;
    private final JobPostApplicationsMapper jobPostApplicationsMapper;
    private final MongoTemplate mongoTemplate;


    @Override
    public List<JobPostApplication> findJobPostApplications(JobPostApplicationsSearchRequest request) {
        Query query = new Query();

        if (request.getJobPostId() != null) {
            query.addCriteria(Criteria.where("jobPostId").is(request.getJobPostId()));
        }
        if (request.getUserId() != null) {
            query.addCriteria(Criteria.where("userId").is(request.getUserId()));
        }
        if (request.getStatus() != null) {
            query.addCriteria(Criteria.where("applicationStatus").is(request.getStatus()));
        }

        if (request.getCompanyId() != null) {
            query.addCriteria(Criteria.where("companyId").is(request.getCompanyId()));
        }

        List<JobPostApplicationEntity> jobPosts = mongoTemplate.find(query, JobPostApplicationEntity.class);
        return jobPosts.stream().map(jobPostApplicationsMapper::toDto).toList();
    }

    @Override
    public JobPostApplication updateJobPostApplication(JobPostApplication jobPostApplication) {
        // check if application exists
        var entity = jobPostApplicationsRepository.findById(jobPostApplication.getId())
                .orElseThrow(() -> new JobPostApplicationDoesNotExistsException(jobPostApplication.getId()));

        entity.setApplicationStatus(JobPostApplicationStatus.valueOf(jobPostApplication.getApplicationStatus()));
        entity.setApplicationStatusReason(jobPostApplication.getApplicationStatusReason());
        entity.setApplicationStatusBy(jobPostApplication.getApplicationStatusBy());
        entity = jobPostApplicationsRepository.save(entity);
        return jobPostApplicationsMapper.toDto(entity);
    }

    @Override
    public List<JobPostApplication> findAllJobPostApplications() {
        return jobPostApplicationsRepository.findAll().stream()
                .map(jobPostApplicationsMapper::toDto).toList();
    }
}
