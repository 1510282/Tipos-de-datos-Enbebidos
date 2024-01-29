package com.ingeniero.criteria.model.example2;


import jakarta.persistence.*;


@Entity
public class Company extends CompanyID {



    @EmbeddedId
    @AttributeOverride(name = "cif", column = @Column(name = "ccompany_cif"))
    private CompanyID id;
    private String Location;
    private Integer employeeNum;


    public Company(CompanyID id, String location, Integer employeeNum) {
        this.id = id;
        Location = location;
        this.employeeNum = employeeNum;
    }

    public Company() {
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", Location='" + Location + '\'' +
                ", employeeNum=" + employeeNum +
                '}';
    }

    public CompanyID getId() {
        return id;
    }

    public void setId(CompanyID id) {
        this.id = id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public Integer getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(Integer employeeNum) {
        this.employeeNum = employeeNum;
    }


}