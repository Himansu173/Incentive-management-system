package com.ims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ims.entity.EmployeeDetailsEntity;
import com.ims.entity.EmployeeHolidayPackageEntity;
import com.ims.repository.EmployeeDetailsRepository;
import com.ims.repository.EmployeeHolidayPackageRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeDetailsRepository employeeDetailsRepository;

    @Autowired
    EmployeeHolidayPackageRepository employeeHolidayPackageRepository;

    public List<com.ims.entity.EmployeeDetailsEntity> getDetailsDashboard() {
        List<EmployeeDetailsEntity> list = employeeDetailsRepository.findAll();
        return list;
    }

    public String saveSale(EmployeeDetailsEntity empDetail) {
        Optional<EmployeeDetailsEntity> emp = employeeDetailsRepository.findById(empDetail.getId());

        EmployeeDetailsEntity details = emp.get();
        Long saleCount = emp.get().getSaleCount() + empDetail.getSaleCount();

        if (saleCount >= 10000 && saleCount < 20000) {
            details.setIncentivePercentage("1.5%");
            details.setBonus("None");
            details.setHolidaypackageEligibility("No");
        } else if (saleCount >= 20000 && saleCount < 30000) {
            details.setIncentivePercentage("3%");
            details.setBonus("None");
            details.setHolidaypackageEligibility("No");
        } else if (saleCount >= 30000 && saleCount < 50000) {
            details.setIncentivePercentage("3.5%");
            details.setBonus("$1000");
            details.setHolidaypackageEligibility("No");
        } else if (saleCount >= 50000) {
            details.setIncentivePercentage("5%");
            details.setBonus("None");
            details.setHolidaypackageEligibility("Yes");

            // EmployeeHolidayPackageEntity employeeHolidayPackageEntity = new
            // EmployeeHolidayPackageEntity();
            // silver package
            EmployeeHolidayPackageEntity employeeHolidayPackageEntity = employeeHolidayPackageRepository
                    .findByEmployeeName(details.getName());
            if (ObjectUtils.isEmpty(employeeHolidayPackageEntity)) {
                employeeHolidayPackageEntity = new EmployeeHolidayPackageEntity();
            }
            if (saleCount >= 50000 && saleCount < 70000) {
                employeeHolidayPackageEntity.setEmployeeName(details.getName());
                employeeHolidayPackageEntity.setHolidayName("Silver");
            }
            // gold package
            else if (saleCount >= 70000 && saleCount < 100000) {
                employeeHolidayPackageEntity.setEmployeeName(details.getName());
                employeeHolidayPackageEntity.setHolidayName("Gold");
            }
            // dimond package
            else if (saleCount >= 100000 && saleCount < 150000) {
                employeeHolidayPackageEntity.setEmployeeName(details.getName());
                employeeHolidayPackageEntity.setHolidayName("Dimond");

            }
            // platinum package
            else {
                employeeHolidayPackageEntity.setEmployeeName(details.getName());
                employeeHolidayPackageEntity.setHolidayName("Platinum");
            }

            employeeHolidayPackageRepository.save(employeeHolidayPackageEntity);
        } else {
            //
        }
        details.setSaleCount(saleCount);

        employeeDetailsRepository.save(details);

        return "sale has been updated successfully";
    }

    public List<EmployeeHolidayPackageEntity> getEmployeeHolidayPackage() {
        return employeeHolidayPackageRepository.findAll();
    }
}
