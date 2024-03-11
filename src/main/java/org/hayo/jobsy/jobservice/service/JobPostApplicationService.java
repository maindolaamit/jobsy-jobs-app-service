package org.hayo.jobsy.jobservice.service;

import org.hayo.jobsy.dto.job.JobPostApplication;
import org.hayo.jobsy.dto.job.JobPostApplicationsSearchRequest;

import java.util.List;

public interface JobPostApplicationService {

    List<JobPostApplication> findJobPostApplications(JobPostApplicationsSearchRequest request);

    JobPostApplication updateJobPostApplication(JobPostApplication jobPostApplication);

    List<JobPostApplication> findAllJobPostApplications();
}
