package com.doctorservice.entity;

import com.doctorservice.enums.Gender;
import com.doctorservice.enums.IsWorking;
import com.doctorservice.enums.Role;
import com.doctorservice.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Doctor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctorid")
    private Integer doctorId;

    @Column(name = "doctorname", nullable = false)
    private String doctorName;

    private Double rating = 5.0; 

    @Column(name = "doctornumber", nullable = false)
    private String doctorNumber;

    @Column(name = "doctoremail", nullable = false,unique = true)
    private String doctorEmail;

    @Column(nullable = false)
    private String speciaList;

    @Column(name = "doctorpassword", nullable = false)
    private String doctorPassword;

    @Enumerated(EnumType.STRING)
    private IsWorking isWorking = IsWorking.TRUE;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer ratingCount = 1; 

    private Double totalRatingSum = 0.0; 

    public void addRating(double newRating) {
       this.totalRatingSum += newRating;
       this. ratingCount++;
       this. rating = totalRatingSum / ratingCount; 
    }

    public void removeRating(double oldRating) {
        if (ratingCount > 0) {
            totalRatingSum -= oldRating;
            ratingCount--;
            rating = (ratingCount > 0) ? totalRatingSum / ratingCount : 0.0;
        }
    }


    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Double getRating() {
        return rating;
    }

    public String getDoctorNumber() {
        return doctorNumber;
    }

    public void setDoctorNumber(String doctorNumber) {
        this.doctorNumber = doctorNumber;
    }

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public String getSpeciaList() {
        return speciaList;
    }

    public void setSpeciaList(String speciaList) {
        this.speciaList = speciaList;
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword;
    }

    public IsWorking getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(IsWorking isWorking) {
        this.isWorking = isWorking;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }
}
