package com.group_e.sales_tdd_backend.repository;

import com.group_e.sales_tdd_backend.entity.CustomerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, UUID> {
    Optional<CustomerGroup> findByName(String name);
}
