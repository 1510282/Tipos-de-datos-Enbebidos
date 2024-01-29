package com.ingeniero;

import com.ingeniero.criteria.HibernateUtil;
import org.junit.jupiter.api.Test;
import com.ingeniero.criteria.model.example1.Employee;
import com.ingeniero.criteria.model.EmployeeCategory;

public class enumTest {

    @Test
    void name(){

        insertarData();

    var session = HibernateUtil.getSessionFactory().openSession();

    var emp1 = session.find(Employee.class, 1L);
        System.out.println(emp1);
    }

    @Test
    void findByCategory(){

        insertarData();

        var session = HibernateUtil.getSessionFactory().openSession();
        String jpql = "SELECT e FROM Employee e WHERE e.category = :category";

        session.createQuery(jpql, Employee.class)
                .setParameter("category", EmployeeCategory.JUNIOR)
                .getResultList()
                .forEach(System.out::println);
    }

    void insertarData(){
        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var emp1 = new Employee("John Doe", "12345678", "25", EmployeeCategory.JUNIOR);
        var emp2 = new Employee("Jake Lord", "87654321", "30", EmployeeCategory.SENIOR);
        var emp3 = new Employee("Juan Perez", "12345678", "35", EmployeeCategory.MANAGER);
        var emp4 = new Employee("Maria Perez", "87654321", "40", EmployeeCategory.C_LEVEL);

        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);
        session.persist(emp4);

        session.getTransaction().commit();

    }
}