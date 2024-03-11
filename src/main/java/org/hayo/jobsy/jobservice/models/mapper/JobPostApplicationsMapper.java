package org.hayo.jobsy.jobservice.models.mapper;

import org.hayo.jobsy.dto.job.JobPostApplication;
import org.hayo.jobsy.jobservice.models.entity.JobPostApplicationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobPostApplicationsMapper {

    JobPostApplication toDto(JobPostApplicationEntity entity);

    JobPostApplicationEntity toEntity(JobPostApplication dto);
}
