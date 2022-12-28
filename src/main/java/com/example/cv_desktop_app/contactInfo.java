package com.example.cv_desktop_app;

public class contactInfo {

    private String telephoneNo;
    private String address;
    private String email;

    public contactInfo(String telephoneNo, String address, String email) {
        this.telephoneNo = telephoneNo;
        this.address = address;
        this.email = email;
    }

    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "contactInfo{" +
                "telephoneNo='" + telephoneNo + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
