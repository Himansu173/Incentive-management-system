package com.ims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.entity.EmployeeDetailsEntity;
import com.ims.entity.EmployeeHolidayPackageEntity;
import com.ims.service.EmployeeService;

@RestController
@CrossOrigin
public class EmployeeDetailsController {

    @Autowired
    EmployeeService empService;

    @GetMapping("/getDetailDashboard")
    public ResponseEntity<List<EmployeeDetailsEntity>> getDetailsDashboard() {
        return  ResponseEntity.ok().body(empService.getDetailsDashboard());
     
    }

    @PostMapping("/saveSale")
    public ResponseEntity<String> saveSale(@RequestBody EmployeeDetailsEntity empDetail) {
        return ResponseEntity.ok().body(empService.saveSale(empDetail));
     
    }
    
    
    @GetMapping("/getEmployeeHolidayPackage")
    public ResponseEntity<List<EmployeeHolidayPackageEntity>> getEmployeeHolidayPackage() {
        return  ResponseEntity.ok().body(empService.getEmployeeHolidayPackage());
     
    }

}
