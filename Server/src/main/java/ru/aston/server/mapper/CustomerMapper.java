package ru.aston.server.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import ru.aston.server.CustomerData.GrpcCustomer;
import ru.aston.server.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  GrpcCustomer toGrpc(Customer customer);

  List<GrpcCustomer> toGrpcList(List<Customer> customerList);

}
