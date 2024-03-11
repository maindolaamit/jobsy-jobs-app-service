package org.hayo.jobsy.jobservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hayo.jobsy.dto.job.JobPost;
import org.hayo.jobsy.dto.job.JobPostApplication;
import org.hayo.jobsy.dto.job.JobPostsSearchRequest;
import org.hayo.jobsy.dto.job.JobStatusUpdateRequest;
import org.hayo.jobsy.jobservice.service.JobsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/jobs")
@Slf4j
@Tag(name = "Jobs", description = "Jobs Module")
@AllArgsConstructor
public class JobsController {

    private final JobsService jobsService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create Job", description = "API to create a job.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully verified",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<JobPost> createJob(@Valid @RequestBody JobPost jobPost) {
        log.info("Creating Job {}", jobPost);
        val job = jobsService.createJob(jobPost);
        return new ResponseEntity<>(job, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Job by ID", description = "API to get job by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<JobPost> getJobById(@PathVariable String id) {

        return new ResponseEntity<>(jobsService.getJobById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Jobs", description = "API to get all jobs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<JobPost>> getAllJobs() {
        val allJobs = jobsService.getAllJobs();
        return new ResponseEntity<>(allJobs, HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Job Status", description = "API to update job status.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<JobPost> updateJobStatus(@Valid
                                                   @PathVariable @NotBlank String id,
                                                   @RequestBody JobStatusUpdateRequest request) {
        return new ResponseEntity<>(jobsService.updateJobStatus(id, request), HttpStatus.OK);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search Jobs", description = "API to search jobs.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<JobPost>> findJobsFromCompany(@Valid @RequestBody JobPostsSearchRequest request) {
        return new ResponseEntity<>(jobsService.findJobs(request), HttpStatus.OK);
    }

    @GetMapping("/{jobPostId}/applications")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Job Applications", description = "API to get job applications.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<JobPostApplication> getJobPostApplications(@Valid
                                                                     @Size(min = 5, message = "Job post id is required") @PathVariable(name = "jobPostId") String jobPostId
    ) {
        val jobPostApplications = jobsService.getJobPostApplications(jobPostId);
        return new ResponseEntity(jobPostApplications, HttpStatus.OK);
    }


    @GetMapping("/recommendations/{userId}/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Job Recommendations", description = "API to get job recommendations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public String getJobRecommendations(@PathVariable(name = "userId") String userId) {
        return "Job Recommendations";
    }
}
