package com.ims.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.entity.HolidayPackageDetailsEntity;

@Repository
public interface HolidayPackageDetailsRepository extends JpaRepository<HolidayPackageDetailsEntity,Long>  {

}
