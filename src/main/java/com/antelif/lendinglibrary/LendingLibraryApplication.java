package com.antelif.lendinglibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


@EntityScan(basePackages = "com.antelif.lendinglibrary.*")
@SpringBootApplication
public class LendingLibraryApplication {

  public static void main(String[] args) {
    SpringApplication.run(LendingLibraryApplication.class, args);
  }
}
