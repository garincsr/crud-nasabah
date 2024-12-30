package com.crud.model;

import com.crud.utils.IDMustBeUnique;
import com.crud.utils.NIKMustBeUnique;
import com.crud.utils.PhoneNumberMustBeUnique;

import java.util.List;

public class Nasabah {
    private Integer id;
    private String fullName;
    private String nik;
    private String phoneNumber;
    private String birthDate;

    public Nasabah(Integer id, String fullName, String nik, String phoneNumber, String birthDate) {
        this.id = id;
        this.fullName = fullName;
        this.nik = nik;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Nasabah{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", nik='" + nik + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }

    //======== Validation
    public static void validationCreate(Integer id, String nik, String phoneNumber, List<Nasabah> nasabahList) throws RuntimeException{
        for (Nasabah n : nasabahList){
            if (n != null && n.getId().equals(id)){
                throw new IDMustBeUnique("ID must be unique");
            }

            if (n != null && n.getNik().equals(nik)){
                throw new NIKMustBeUnique("NIK must be unique");
            }

            if (n != null && n.getPhoneNumber().equals(phoneNumber)){
                throw new PhoneNumberMustBeUnique("Phone Number must be unique");
            }
        }
    }
}
