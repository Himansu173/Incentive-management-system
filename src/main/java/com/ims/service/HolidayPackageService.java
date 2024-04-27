package com.ims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ims.entity.HolidayPackageDetailsEntity;
import com.ims.repository.HolidayPackageDetailsRepository;

@Service
public class HolidayPackageService {

	@Autowired
	HolidayPackageDetailsRepository holidayPackageDetailsRepository;

	public List<HolidayPackageDetailsEntity> getHolidayPackage() {
		List<HolidayPackageDetailsEntity> list = holidayPackageDetailsRepository.findAll();
		return list;
	}

	public String saveHolidayPackage(HolidayPackageDetailsEntity holidayPackage) {
		holidayPackageDetailsRepository.save(holidayPackage);
		return "Package added successfully";

	}

	public String updateHolidayPackage(HolidayPackageDetailsEntity holidayPackage) {
		if(holidayPackage.getId() != null) {
			Optional<HolidayPackageDetailsEntity> byId = holidayPackageDetailsRepository.findById(holidayPackage.getId());
			HolidayPackageDetailsEntity holidayPackageDetailsEntity = byId.get();
			
			BeanUtils.copyProperties(holidayPackage, holidayPackageDetailsEntity);
			holidayPackageDetailsRepository.save(holidayPackageDetailsEntity);
		}
	
		return "Package updated successfully";
	}

	public String deleteHolidayPackage(Long id) {
		holidayPackageDetailsRepository.deleteById(id);
		return "Package deleted successfully";
	}

}
