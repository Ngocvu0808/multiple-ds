package com.multipleds.repository.container;

import com.multipleds.entity.container.BaseDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDepartmentRepository extends JpaRepository<BaseDepartment, Integer> {
}
