package com.ingeniero;

import com.ingeniero.criteria.HibernateUtil;
import com.ingeniero.criteria.model.example1.Address;
import com.ingeniero.criteria.model.example1.Customer;
import com.ingeniero.criteria.model.example1.Employee;
import org.junit.jupiter.api.Test;

public class EmbeddableTest {


    @Test
    void name(){
    insertData();

    var session = HibernateUtil.getSessionFactory().openSession();

    var c1 = session.find(Customer.class, 1L);
        System.out.println(c1);

        var e1 = session.find(Employee.class, 1L);
        System.out.println(e1);

    }


    void insertData(){


        var session = HibernateUtil.getSessionFactory().openSession();

        var transaction = session.beginTransaction();

        var address1 = new Address("street 1", "city 1", "country 1");
        var address2 = new Address("street 2", "city 2", "country 2");

        var cust1 = new Customer("cust 1", "age 1", address1);
        var cust2 = new Customer("cust 2", "age 2", address2);

        var emp1 = new Employee("emp 1", "age 1", address1);
        var emp2 = new Employee("emp 2", "age 2", address2);

        session.persist(cust1);
        session.persist(cust2);
        session.persist(emp1);
        session.persist(emp2);

        session.getTransaction().commit();
    }
}
