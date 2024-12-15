package ru.aston.client.grpc;

import java.util.Iterator;
import java.util.List;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.aston.server.CustomerData;
import ru.aston.server.CustomerData.GrpcCustomer;
import ru.aston.server.CustomerData.ListGrpcCustomer;
import ru.aston.server.CustomerData.VoidCustomerRequest;
import ru.aston.server.CustomerServiceGrpc;

@Service
public class CustomerClientGrpc {

  @GrpcClient("customerGrpcService")
  private CustomerServiceGrpc.CustomerServiceBlockingStub customerGrpcService;

  public List<GrpcCustomer> findAllCustomer() {
    return customerGrpcService.findAllCustomers(VoidCustomerRequest.newBuilder().build())
        .getGrpcCustomersList();
  }

  // если принимаем поток данных, то должны принять Iterator<GrpcResponse>
  //  rpc findAllCustomers(stream VoidCustomerRequest) returns (stream ListGrpcCustomer);
//  public Iterator<ListGrpcCustomer> findAllCustomer() {
//    return customerGrpcService.findAllCustomers(VoidCustomerRequest.newBuilder().build());
//  }

  public GrpcCustomer findById(Long id) {
    return customerGrpcService.findCustomerById(
        CustomerData.IdCustomerRequest
            .newBuilder().setId(id)
            .build());
  }

}
