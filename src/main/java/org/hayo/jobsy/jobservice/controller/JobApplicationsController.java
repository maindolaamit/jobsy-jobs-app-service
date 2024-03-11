package org.hayo.jobsy.jobservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hayo.jobsy.dto.job.JobPostApplication;
import org.hayo.jobsy.dto.job.JobPostApplicationsSearchRequest;
import org.hayo.jobsy.jobservice.service.JobPostApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/applications")
@Slf4j
@Tag(name = "JobApplications", description = "JobApplications Module")
@AllArgsConstructor
public class JobApplicationsController {

    private final JobPostApplicationService jobPostApplicationService;

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All Job Applications", description = "API to get All  job applications.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<JobPostApplication>> getAllJobPostApplications() {
        val applications = jobPostApplicationService.findAllJobPostApplications();
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }
    @GetMapping("/search")
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
    public ResponseEntity<List<JobPostApplication>> getJobPostApplications(@Valid @RequestBody JobPostApplicationsSearchRequest request) {
        val applications = jobPostApplicationService.findJobPostApplications(request);
        return new ResponseEntity<>(applications, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update Application", description = "API to update application.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<JobPostApplication> updateApplicationById(@Valid @RequestBody JobPostApplication jobPostApplication) {
        jobPostApplicationService.updateJobPostApplication(jobPostApplication);
        return new ResponseEntity<>(jobPostApplication, HttpStatus.OK);
    }

}
