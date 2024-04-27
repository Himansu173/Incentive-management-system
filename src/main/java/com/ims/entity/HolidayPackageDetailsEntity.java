package com.ims.entity;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;


@Entity
@Table(name = "HOLIDAY_PACKAGE_DETAILS")
public class HolidayPackageDetailsEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="HOLIDAY_NAME")
    private String holidayName;
    @Column(name="DURATION")
    private String duration;
    @Column(name="DESTINATION")
    private String destination;
    @Column(name="LOCATION")
    private String location;
    @Column(name="AMENITIES")
    private String amenities;
    @Column(name="ELIGIBILITY_SALE_COUNT")
    private String eligibilitySaleCount;
    
    
    public String getEligibilitySaleCount() {
		return eligibilitySaleCount;
	}
	public void setEligibilitySaleCount(String eligibilitySaleCount) {
		this.eligibilitySaleCount = eligibilitySaleCount;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getHolidayName() {
        return holidayName;
    }
    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getAmenities() {
        return amenities;
    }
    public void setAmenities(String amenities) {
        this.amenities = amenities;
    }
    @Override
	public String toString() {
		return "HolidayPackageDetailsEntity [id=" + id + ", holidayName=" + holidayName + ", duration=" + duration
				+ ", destination=" + destination + ", location=" + location + ", amenities=" + amenities
				+ ", eligibilitySaleCount=" + eligibilitySaleCount + "]";
	}

    

}
