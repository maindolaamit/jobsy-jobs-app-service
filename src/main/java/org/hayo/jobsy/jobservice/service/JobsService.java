package org.hayo.jobsy.jobservice.service;

import org.hayo.jobsy.dto.job.JobPost;
import org.hayo.jobsy.dto.job.JobPostApplication;
import org.hayo.jobsy.dto.job.JobPostsSearchRequest;
import org.hayo.jobsy.dto.job.JobStatusUpdateRequest;

import java.util.List;

public interface JobsService {


    JobPost createJob(JobPost jobPost);

    List<JobPost> getAllJobs();

    JobPost getJobById(String id);

    JobPost updateJobStatus(String id, JobStatusUpdateRequest request);

    List<JobPost> findJobs(JobPostsSearchRequest request);

    List<JobPostApplication> getJobPostApplications(String jobId);
}
