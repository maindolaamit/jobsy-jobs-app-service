package org.hayo.jobsy.jobservice.models.exception;

import org.hayo.jobsy.models.exceptions.AbstractWebExceptions;
import org.springframework.http.HttpStatus;

public class JobPostDoesNotExistsException extends AbstractWebExceptions {

    public JobPostDoesNotExistsException(String id) {
        super(String.format("%s - Job Post do not exists", id), HttpStatus.CONFLICT);
    }
}
