package ru.aston.server.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.aston.server.exception.CustomerNotFoundException;
import ru.aston.server.mapper.CustomerMapper;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.aston.server.CustomerData.GrpcCustomer;
import ru.aston.server.CustomerData.IdCustomerRequest;
import ru.aston.server.CustomerData.ListGrpcCustomer;
import ru.aston.server.CustomerData.VoidCustomerRequest;
import ru.aston.server.CustomerServiceGrpc;
import ru.aston.server.model.Customer;
import ru.aston.server.service.CustomerService;

@GrpcService
@RequiredArgsConstructor
public class CustomerGrpcServer extends CustomerServiceGrpc.CustomerServiceImplBase {

  private final CustomerService customerService;
  private final CustomerMapper customerMapper;

  @Override
  public void findAllCustomers(VoidCustomerRequest request,
      StreamObserver<ListGrpcCustomer> responseObserver) {
    List<Customer> customerList = customerService.findAll();
    List<GrpcCustomer> grpcCustomerList = customerMapper.toGrpcList(customerList);

    ListGrpcCustomer listGrpcCustomer =
        ListGrpcCustomer.newBuilder().addAllGrpcCustomers(grpcCustomerList).build();
    responseObserver.onCompleted();
  }

  // для потоковой передачи:
  // rpc findAllCustomers(stream VoidCustomerRequest) returns (stream ListGrpcCustomer);
//  @Override
//  public StreamObserver<VoidCustomerRequest> findAllCustomers(
//      StreamObserver<ListGrpcCustomer> responseObserver) {
//    return new StreamObserver<VoidCustomerRequest>() {
//      @Override
//      public void onNext(VoidCustomerRequest voidCustomerRequest) {
//        responseObserver.onNext(ListGrpcCustomer.newBuilder().addAllGrpcCustomers(
//            customerMapper.toGrpcList(customerService.findAll()))
//            .build());
//      }
//
//      @Override
//      public void onError(Throwable throwable) {
//
//      }
//
//      @Override
//      public void onCompleted() {
//
//      }
//    };
//  }

  @Override
  public void findCustomerById(IdCustomerRequest request,
      StreamObserver<GrpcCustomer> responseObserver) {
    /*
      Прямая обработка ошибок и ответов клиенту на ошибку
     */
//    if (!customerService.existsById(request.getId())) {
//      responseObserver.onError(Status.NOT_FOUND.withDescription(
//          "The Customer with id = %s could not be found.".formatted(request.getId()))
//          .asRuntimeException());
//      return;
//    }
    if (!customerService.existsById(request.getId())) {
      throw new CustomerNotFoundException();
    }

    Customer customer = customerService.findById(request.getId());
    GrpcCustomer grpcCustomer = customerMapper.toGrpc(customer);
    responseObserver.onNext(grpcCustomer);
    responseObserver.onCompleted();
  }


}
