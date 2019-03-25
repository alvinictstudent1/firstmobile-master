package com.example.mobileapplication;

public class Address {
    String address_line_1;
    String address_line_2;
    String care_of;
    String country;
    String locality;
    String po_box;
    String postal_code;
    String region;

    public Address(String address_line_1, String address_line_2, String care_of, String country, String locality, String po_box, String postal_code, String region){
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.care_of = care_of;
        this.country = country;
        this.locality = locality;
        this.po_box = po_box;
        this.postal_code = postal_code;
        this.region = region;
    }
}
