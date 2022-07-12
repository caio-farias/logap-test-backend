package com.projects.logapTest.RetailAPI.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public class ApiException {
  private final String message;
  private final HttpStatus httpStatus;
  private final String timestamp;
}
