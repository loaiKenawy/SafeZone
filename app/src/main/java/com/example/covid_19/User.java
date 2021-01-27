package com.example.covid_19;

import com.google.firebase.database.DatabaseReference;

public class User {



    private int  age;

    private String firstName , lastName, emailAddress , password;


    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void Register(String fName , String lName , String email, String inPassword ,int age){

            setFirstName(fName);

            setLastName(lName);

            setEmailAddress(email);

            setPassword(inPassword);

            setAge(age);




    }
    public int login(String email , String inPassword) {

        if (email.equals(getEmailAddress()) && inPassword.equals(getPassword())) {
            return 1;

        } else if (email.isEmpty() || inPassword.isEmpty()) {

            return 0;
        }
        else
            return -1;

    }
}


