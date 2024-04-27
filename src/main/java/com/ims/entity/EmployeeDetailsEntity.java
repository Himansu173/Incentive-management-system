package com.ims.entity;

import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetailsEntity {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="SALE_COUNT")
    private Long saleCount;

    @Column(name="INCENTIVE_PERCENTAGE")
    private String incentivePercentage;
    @Column(name="BONUS")
    private String bonus;
    @Column(name="HOLIDAY_PACKAGE_ELIGIBILITY")
    private String holidaypackageEligibility;

    

    public String getIncentivePercentage() {
        return incentivePercentage;
    }

    public void setIncentivePercentage(String incentivePercentage) {
        this.incentivePercentage = incentivePercentage;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getHolidaypackageEligibility() {
        return holidaypackageEligibility;
    }

    public void setHolidaypackageEligibility(String holidaypackageEligibility) {
        this.holidaypackageEligibility = holidaypackageEligibility;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Long getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Long saleCount) {
        this.saleCount = saleCount;
    }

    @Override
    public String toString() {
        return "EmployeeDetailsEntity [id=" + id + ", name=" + name + ", saleCount=" + saleCount
                + ", incentivePercentage=" + incentivePercentage + ", bonus=" + bonus + ", holidaypackageEligibility="
                + holidaypackageEligibility + "]";
    }



   

    
}
