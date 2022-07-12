package com.projects.logapTest.RetailAPI.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiRequestExceptionHandler {
  @ExceptionHandler(
    value = { ApiRequestException.class }
  )
  public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
    HttpStatus badReq = HttpStatus.BAD_REQUEST;
    ApiException apiException = new ApiException(
      e.getMessage(),
      badReq,
      ZonedDateTime.now(ZoneId.of("UTC")).toLocalDateTime().toString()
    );
    return new ResponseEntity<>(
      apiException,
      badReq
    );
  }
}
