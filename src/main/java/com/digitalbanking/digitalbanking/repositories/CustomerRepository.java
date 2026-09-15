package com.digitalbanking.digitalbanking.repositories;

import com.digitalbanking.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
