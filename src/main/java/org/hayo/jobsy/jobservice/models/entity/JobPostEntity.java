package org.hayo.jobsy.jobservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hayo.jobsy.enums.jobs.JobPostStatus;
import org.hayo.jobsy.enums.jobs.JobType;
import org.hayo.jobsy.models.entity.AbstractEntity;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "job_posts")
public class JobPostEntity extends AbstractEntity {
    private JobType jobType;
    private String jobTitle;
    private String aboutJob;
    private String location;
    private String companyId;
    private String skills;
    private JobPostStatus status;
}
