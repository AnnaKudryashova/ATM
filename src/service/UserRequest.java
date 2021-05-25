package service;

import exception.FailedServiceException;

public interface UserRequest {
    void execute() throws FailedServiceException, InterruptedException;
}
