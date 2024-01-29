package com.ingeniero.criteria.model.example2;

import jakarta.persistence.Embeddable;

@Embeddable
public class CompanyID implements java.io.Serializable {

    private static final long serialVersionUID = -85024857082708245L;
    private String cif;
    private String brand;


    public CompanyID(String cif, String brand) {
        this.cif = cif;
        this.brand = brand;
    }

    public CompanyID() {
    }

    @Override
    public String toString() {
        return "CompanyID{" +
                "cif='" + cif + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }


    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
