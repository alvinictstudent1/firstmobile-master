package com.example.mobileapplication;

import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class CompanyData {
    private final String company_number;
    private final String company_name;

    public CompanyData(JSONObject companyData) throws JSONException {
        this.company_number = companyData.getString("company_number");
        this.company_name = companyData.getString("title");
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_number() {
        return company_number;
    }
}