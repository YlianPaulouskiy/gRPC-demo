package ru.aston.server.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

  private Long id;

  private String name;

  private String lastName;

  private LocalDate birthday;

  @Builder.Default
  private LocalDateTime created = LocalDateTime.now();

}
