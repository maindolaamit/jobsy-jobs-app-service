package org.hayo.jobsy.jobservice.models.mapper;

import org.hayo.jobsy.dto.job.JobPost;
import org.hayo.jobsy.enums.jobs.JobPostStatus;
import org.hayo.jobsy.jobservice.models.entity.JobPostEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JobPostsMapper {

    JobPost toDto(JobPostEntity entity);

    JobPostEntity toEntity(JobPost dto);
    @Mapping(target = "status", ignore = true, defaultValue = "ACTIVE ")
    JobPostEntity toNewEntity(JobPost dto);

    @AfterMapping
    default void afterMapping(JobPostEntity entity, JobPost dto) {
        dto.setStatus(entity.getStatus().getValue());
        dto.setJobType(entity.getJobType().getValue());
    }
}
