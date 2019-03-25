package com.example.mobileapplication;
import android.graphics.Bitmap;
import java.util.ArrayList;

public class comOfficer {

    public String officerName;
    public String company_number;
    public String officer_role;
    public String occupation;
    public String nationality;
    Address address;



    //setter for officer
    public void setOfficerName (String setName){
        this.officerName = setName;
    }

    public void setCompany_number(String company_number){
        this.company_number = company_number;
    }
    public void setOfficer_role(String officer_role){
        this.officer_role = officer_role;
    }
    public void setOccupation(String occupation){
        this.occupation = occupation;
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    }
    public void setAddress(Address address){
        this.address = address;
    }

    //the getter
    public String getCompanyData() {
        return company_number;
    }
    public String getOfficerName(){
        return officerName;
    }
    public String getCompany_number(){
        return company_number;
    }
    public String getOfficer_role(){
        return officer_role;
    }
    public String getOccupation(){
        return occupation;
    }
    public String getNationality(){
        return nationality;
    }
}



