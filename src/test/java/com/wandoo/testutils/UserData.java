package com.wandoo.testutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class UserData {
    private String email;
    private String password;
    private String phone;
    private String phonePrefix;

    private String firstName;
    private String lastName;
    private String birthDate;
    private String passportNumber;
    private String passportExpDate;
    private String address;
    private String country;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getPassportExpDate() {
        return passportExpDate;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassportExpDate(String passportExpDate) {
        this.passportExpDate = passportExpDate;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public UserData() {
        DateTimeFormatter timeStampFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = LocalDateTime.now().format(timeStampFormatter);
        email = String.format("testuser%s@qa.com", timestamp);
        password = String.format("TestUser%s@qa.com", timestamp);
        phone = timestamp.substring(6); // this makes 8 numbers as phone from timestamp
        phonePrefix = "+371";

        firstName = "Test";
        lastName = "User";
        birthDate = "01-01-2000";
        address = "100 Brivibas iela Riga Latvia";
        country = "Latvia";
        passportNumber = timestamp;
        passportExpDate = "01-01-2999";
    }
}
