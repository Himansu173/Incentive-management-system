package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.entity.EmployeeDetailsEntity;


@Repository
public interface EmployeeDetailsRepository  extends JpaRepository<EmployeeDetailsEntity,Long> {


}
