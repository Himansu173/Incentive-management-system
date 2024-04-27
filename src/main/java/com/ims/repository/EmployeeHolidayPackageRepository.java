package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.entity.EmployeeHolidayPackageEntity;

@Repository
public interface EmployeeHolidayPackageRepository extends JpaRepository<EmployeeHolidayPackageEntity,Long>  {
    EmployeeHolidayPackageEntity findByEmployeeName(String name);
}
