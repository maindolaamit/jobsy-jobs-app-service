package org.hayo.jobsy.jobservice.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hayo.jobsy.dto.job.JobPost;
import org.hayo.jobsy.dto.job.JobPostApplication;
import org.hayo.jobsy.dto.job.JobPostsSearchRequest;
import org.hayo.jobsy.dto.job.JobStatusUpdateRequest;
import org.hayo.jobsy.enums.jobs.JobPostStatus;
import org.hayo.jobsy.jobservice.models.entity.JobPostEntity;
import org.hayo.jobsy.jobservice.models.exception.JobPostDoesNotExistsException;
import org.hayo.jobsy.jobservice.models.mapper.JobPostApplicationsMapper;
import org.hayo.jobsy.jobservice.models.mapper.JobPostsMapper;
import org.hayo.jobsy.jobservice.repository.JobPostApplicationsRepository;
import org.hayo.jobsy.jobservice.repository.JobPostsRepository;
import org.hayo.jobsy.jobservice.service.JobsService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class JobsServiceImpl implements JobsService {

    private final MongoTemplate mongoTemplate;
    private final JobPostsRepository jobPostsRepository;
    private final JobPostsMapper jobPostMapper;
    private final JobPostApplicationsMapper jobPostApplicationsMapper;
    private final JobPostApplicationsRepository jobPostApplicationsRepository;

    @Override
    public JobPost createJob(JobPost jobPost) {
        log.info("Creating job post: {}", jobPost);

        val entity = jobPostsRepository.save(jobPostMapper.toNewEntity(jobPost));
        return jobPostMapper.toDto(entity);
    }

    @Override
    public List<JobPost> getAllJobs() {
        return jobPostsRepository.findAll().stream().map(jobPostMapper::toDto).toList();
    }

    @Override
    public JobPost getJobById(String id) {
        // check if job exists
        val entity = jobPostsRepository.findById(id).orElseThrow(() -> new JobPostDoesNotExistsException(id));
        return jobPostMapper.toDto(entity);
    }

    @Override
    public JobPost updateJobStatus(String id, JobStatusUpdateRequest request) {
        // check if job exists
        val entity = jobPostsRepository.findById(id).orElseThrow(() -> new JobPostDoesNotExistsException(id));
        entity.setStatus(JobPostStatus.valueOf(request.getStatus()));
        return jobPostMapper.toDto(entity);
    }

    @Override
    public List<JobPost> findJobs(JobPostsSearchRequest request) {
        Query query = new Query();

        if (request.getJobTitle() != null) {
            query.addCriteria(Criteria.where("jobTitle").is(request.getJobTitle()));
        }
        if (request.getJobType() != null) {
            query.addCriteria(Criteria.where("jobType").is(request.getJobType()));
        }
        if (request.getLocation() != null) {
            query.addCriteria(Criteria.where("location").is(request.getLocation()));
        }

        if (request.getCompanyId() != null) {
            query.addCriteria(Criteria.where("companyId").is(request.getCompanyId()));
        }

        List<JobPostEntity> jobPosts = mongoTemplate.find(query, JobPostEntity.class);
        return jobPosts.stream().map(jobPostMapper::toDto).toList();
    }

    @Override
    public List<JobPostApplication> getJobPostApplications(String jobId) {
        // check if jobId is valid
        val entity = jobPostsRepository.findById(jobId).orElseThrow(() -> new JobPostDoesNotExistsException(jobId));

        return jobPostApplicationsRepository.findAllByJobPostId(jobId)
                .stream().map(jobPostApplicationsMapper::toDto)
                .toList();
    }
}
