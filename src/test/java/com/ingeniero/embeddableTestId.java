package com.ingeniero;

import com.ingeniero.criteria.HibernateUtil;

import com.ingeniero.criteria.model.example2.Company;
import com.ingeniero.criteria.model.example2.CompanyID;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

public class embeddableTestId {


    @Test
    void name(){
        insertData();

        var session = HibernateUtil.getSessionFactory().openSession();

       var companyId1 = new CompanyID("cif 1", "brand 1");
        var companyId2 = new CompanyID("cif 2", "brand 2");

        var company1 = session.find(Company.class, companyId1);
        System.out.println(company1);

        var company2 = session.find(Company.class, companyId2);
        System.out.println(company2);

    }


    void insertData(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var CompanyID1 = new CompanyID("cif 1", "brand 1");
        var CompanyID2 = new CompanyID("cif 2", "brand 2");

        var company1 = new Company(CompanyID1, "location 1" ,1);
        var company2 = new Company(CompanyID2, "location 2", 2);

        session.persist(company1);
        session.persist(company2);

        session.getTransaction().commit();
    }
}
