package com.ims.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ims.entity.HolidayPackageDetailsEntity;
import com.ims.service.HolidayPackageService;


@RestController
@CrossOrigin
public class HolidayPackageController {

    @Autowired
    HolidayPackageService holidayPackageService;

    @GetMapping("/getHolidayPackage")
    public ResponseEntity<List<HolidayPackageDetailsEntity>> getHolidayPackage() {
        return  ResponseEntity.ok().body(holidayPackageService.getHolidayPackage());
     
    }

    @PostMapping("/saveHolidayPackage")
    public ResponseEntity<String> saveHolidayPackage(@RequestBody HolidayPackageDetailsEntity holidayPackage) {
        return ResponseEntity.ok().body(holidayPackageService.saveHolidayPackage(holidayPackage));
     
    }
    
    @PutMapping("/updateHolidayPackage")
    public ResponseEntity<String> updateHolidayPackage(@RequestBody HolidayPackageDetailsEntity holidayPackage) {
        return ResponseEntity.ok().body(holidayPackageService.updateHolidayPackage(holidayPackage));
     
    }

    @DeleteMapping("/deleteHolidayPackage/{id}")
    public ResponseEntity<String> deleteHolidayPackage(@PathVariable Long id ) {
        return ResponseEntity.ok().body(holidayPackageService.deleteHolidayPackage(id));
     
    }
}
