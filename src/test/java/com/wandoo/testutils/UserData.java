package com.wandoo.testutils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("unused")
public class UserData {
    private String email;
    private String password;
    private String phone;

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

    private String phonePrefix;

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
        password = email;
        phone = timestamp;
        phonePrefix = "+371";
    }
}
