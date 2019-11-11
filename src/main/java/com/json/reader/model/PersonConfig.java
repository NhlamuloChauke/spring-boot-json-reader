package com.json.reader.model;

public class PersonConfig {

    private String name;
    private String surname;
    private String companyName;
    private String emailAddress;
    private String contactNumber;

    public PersonConfig(){}

    public PersonConfig(String name, String surname, String companyName, String emailAddress, String contactNumber) {
        this.name = name;
        this.surname = surname;
        this.companyName = companyName;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
