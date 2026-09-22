package com.digitalbanking.digitalbanking.repositories;

import com.digitalbanking.digitalbanking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %:name%")
    List<Customer> searchcustomer(@Param("name") String name);

}
