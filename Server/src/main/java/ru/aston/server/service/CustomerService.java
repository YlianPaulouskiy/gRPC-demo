package ru.aston.server.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.aston.server.model.Customer;

@Service
public class CustomerService {

  private static final List<Customer> customerList;

  static {
    customerList = initCustomerList();
  }

  private static List<Customer> initCustomerList() {
    return List.of(
        Customer.builder()
            .id(1L)
            .name("Sergey")
            .lastName("Shashkov")
            .birthday(LocalDate.of(2000, 12, 10))
            .build(),

        Customer.builder()
            .id(2L)
            .name("Valdimir")
            .lastName("Shashkov")
            .birthday(LocalDate.of(1999, 12, 10))
            .build(),

        Customer.builder()
            .id(3L)
            .name("Roman")
            .lastName("Shashkov")
            .birthday(LocalDate.of(2002, 12, 10))
            .build());
  }


  public List<Customer> findAll() {
    return customerList;
  }


  public Customer findById(Long id) {
    return customerList.stream()
        .filter(customer -> customer.getId().equals(id))
        .findFirst().orElseThrow();
  }

  public boolean existsById(Long id) {
    return customerList
        .stream()
        .anyMatch(customer -> customer.getId().equals(id));
  }
}
