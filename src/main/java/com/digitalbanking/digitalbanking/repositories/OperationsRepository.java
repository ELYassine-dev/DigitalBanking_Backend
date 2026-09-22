package com.digitalbanking.digitalbanking.repositories;

import com.digitalbanking.digitalbanking.DTOs.AccountHistoryDto;
import com.digitalbanking.digitalbanking.entities.Operations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OperationsRepository extends JpaRepository<Operations,Long> {
 List<Operations> findByBankAccountId(String id);
 Page<Operations> findByBankAccountIdOrderByDateDesc(String id, Pageable  pageable);}
