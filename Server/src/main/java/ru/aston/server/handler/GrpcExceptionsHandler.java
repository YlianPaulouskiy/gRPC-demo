package ru.aston.server.handler;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;
import ru.aston.server.exception.CustomerNotFoundException;

@Slf4j
@GrpcAdvice
public class GrpcExceptionsHandler {

  @GrpcExceptionHandler(CustomerNotFoundException.class)
  public StatusRuntimeException handleCustomerNotFoundException(
      CustomerNotFoundException customerNotFoundException) {
    return Status.NOT_FOUND
        .withDescription(customerNotFoundException.getMessage())
        .asRuntimeException();
  }

}
