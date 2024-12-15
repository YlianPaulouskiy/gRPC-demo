package ru.aston.client;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.aston.client.grpc.CustomerClientGrpc;

@SpringBootApplication
public class ClientApplication {

  public static void main(String[] args) {
    var context = SpringApplication.run(ClientApplication.class, args);

    var customerGrpc = context.getBean(CustomerClientGrpc.class);
    System.out.println(customerGrpc.findAllCustomer());
    try {
      var response = customerGrpc.findById(0L);
      System.out.println(response);
    } catch (StatusRuntimeException e) {
      Status status = e.getStatus();
      System.err.println("error code:\t" + status.getCode());
      System.err.println("error description:\t" + status.getDescription());
    }
  }

}
