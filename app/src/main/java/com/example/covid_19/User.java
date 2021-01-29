package com.example.covid_19;

public class User {

    private int  age;

    private String firstName , lastName, phoneNumber, password ;

    boolean Positive;



    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPositive(boolean positive) {
        this.Positive = positive;
    }

    public void Register(String fName , String lName , String email, String inPassword , int age , boolean status){

            setFirstName(fName);

            setLastName(lName);

            setPhoneNumber(email);

            setPassword(inPassword);

            setAge(age);

            setPositive(status);
    }

}


