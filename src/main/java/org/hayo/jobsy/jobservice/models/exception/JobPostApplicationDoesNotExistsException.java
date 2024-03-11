package org.hayo.jobsy.jobservice.models.exception;

import org.hayo.jobsy.models.exceptions.AbstractWebExceptions;
import org.springframework.http.HttpStatus;

public class JobPostApplicationDoesNotExistsException extends AbstractWebExceptions {
    public JobPostApplicationDoesNotExistsException(String id) {
        super("Job post application does not exists with id: " + id, HttpStatus.BAD_REQUEST);
    }
}
