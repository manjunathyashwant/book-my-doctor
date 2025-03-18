package com.tp.bookmydoctor.responsedto;

import java.util.List;

import lombok.Data;

@Data
public class UserResponseDto {
	
    private String userName;
    private String  email;
    private Integer userAge;
    private String phoneNo;
    private String password="**********";
    

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", userAge=" + userAge +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

