package com.ingeniero.criteria.model;

import com.ingeniero.criteria.converter.CategoryConverter;
import com.mysql.cj.protocol.ColumnDefinition;
import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String dni;

    private String age;

   // @Enumerated(EnumType.STRING)
    //@Column(columnDefinition = "varchar(30) default 'JUNIOR'")
    @Enumerated(EnumType.ORDINAL)
    @Convert(converter = CategoryConverter.class)
    @Column(columnDefinition =  "smallint default 1")
    private EmployeeCategory category;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", age='" + age + '\'' +
                ", category=" + category +
                '}';
    }

    public Employee(String name, String dni, String age, EmployeeCategory category) {
        this.name = name;
        this.dni = dni;
        this.age = age;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public EmployeeCategory getCategory() {
        return category;
    }

    public void setCategory(EmployeeCategory category) {
        this.category = category;
    }
}
