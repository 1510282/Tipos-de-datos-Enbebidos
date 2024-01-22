package com.ingeniero;

import com.ingeniero.jpql.model.Author;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class QueryTest {

    @Test
    void findAll(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();


        String jpql = "SELECT a FROM Author a";

     var query =  session.createQuery(jpql, Author.class);

     query.list().forEach(System.out::println);

    }

@Test
    void findByEmail(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();


        String jpql = "SELECT a FROM Author a where a.email = :email";

        var query =  session.createQuery(jpql, Author.class);
        query.setParameter("email", "a2@email");
        var author = query.getSingleResult();
        System.out.println(author);

    }
@Test
    void findByDate(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();


        String jpql = "SELECT a FROM Author a where a.birthDate between :startDate and :endDate";

        var query =  session.createQuery(jpql, Author.class);
        query.setParameter("startDate", LocalDate.of(1980, 1, 1));
        query.setParameter("endDate", LocalDate.of(1990, 1, 1));
    query.list().forEach(System.out::println);

    }

    @Test
    void findByIdIn(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();


        String jpql = "SELECT a FROM Author a where a.id in (:ids)";

        var query =  session.createQuery(jpql, Author.class);
       query.setParameter("ids", List.of(1L, 3L));
        query.list().forEach(System.out::println);
    }


    @Test
    void findeByCity(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();


        String jpql = "SELECT a FROM Author a where a.address.city = :city";

        var query =  session.createQuery(jpql, Author.class);
        query.setParameter("city", "Madrid");
        query.list().forEach(System.out::println);

    }


    @Test
    void count(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();


        String jpql = "SELECT count(a.id)  FROM Author a ";
        var query =  session.createQuery(jpql, Long.class);

        long count = query.getSingleResult();

        System.out.println("numero de autores: " + count);



    }

    @Test
    void update(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();

        String jpql = "UPDATE Author a SET a.name = :name WHERE a.id = :id";
        session.beginTransaction();
        int update =  session.createMutationQuery(jpql).setParameter("name", "Juan").setParameter("id", 1L).executeUpdate();
        session.getTransaction().commit();
        System.out.println("numero de autores actualizados: " + update);
    }

    @Test
    void delete(){
        insertData();
        var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();

        String jpql = "delete from Author a WHERE a.id = :id";
        session.beginTransaction();
        int delete =  session.createMutationQuery(jpql).setParameter("id", 1L).executeUpdate();
        session.getTransaction().commit();
        System.out.println("numero de autores borrados: " +delete);
    }

   void insertData(){
    var session = com.ingeniero.jpql.HibernateUtil.getSessionFactory().openSession();

    session.beginTransaction();

    var address1 = new com.ingeniero.jpql.model.Address("Calle 1", "Madrid", "Pais");
    var address2 = new com.ingeniero.jpql.model.Address("Calle 2", "Barcelona", "Pais");
    var address3 = new com.ingeniero.jpql.model.Address("Calle 3", "Madrid", "Pais");

    session.persist(address1);
    session.persist(address2);
    session.persist(address3);

    var author1 = new Author("a1", "a1@email", LocalDate.of(1980, 1, 1));
    var author2 = new Author("a2", "a2@email", LocalDate.of(1990, 1, 1));
    var author3 = new Author("a3", "a3@gmail", LocalDate.of(1995, 1, 1));

    author1.setAddress(address1);
    author2.setAddress(address2);
    author3.setAddress(address3);

    session.persist(author1);
    session.persist(author2);
    session.persist(author3);

    session.getTransaction().commit();
   }

}
