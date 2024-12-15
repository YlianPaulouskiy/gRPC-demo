package ru.aston.server.exception;

public class CustomerNotFoundException extends RuntimeException {

  public CustomerNotFoundException() {
    this("Customer could not be found.");
  }

  public CustomerNotFoundException(String message) {
    super(message);
  }
}
