package org.hayo.jobsy.jobservice.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hayo.jobsy.enums.jobs.JobPostApplicationStatus;
import org.hayo.jobsy.models.entity.AbstractEntity;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document(collection = "job_posts_application")
@CompoundIndex(name = "job_user_index", def = "{'jobId': 1, 'userId': 1}", unique = true)
public class JobPostApplicationEntity extends AbstractEntity {
    private String id;
    private String jobPostId;
    private String userId;
    private String resumeUrl;
    private JobPostApplicationStatus applicationStatus;
    private LocalDateTime applicationDate;
    private String applicationStatusBy;
    private String applicationStatusReason;
}
