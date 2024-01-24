package com.ingeniero;

import com.ingeniero.criteria.HibernateUtil;
import com.ingeniero.criteria.model.Author;
import com.ingeniero.criteria.model.Book;
import jakarta.persistence.criteria.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class criteriaTest {

    @Test
    public void findAll() {
    insertData();
    var session = HibernateUtil.getSessionFactory().openSession();
        var builder = session.getCriteriaBuilder();
        var criteria =  builder.createQuery(Author.class);

        criteria.select(criteria.from(Author.class));

       // criteria.select(root);
        // Root<Author > root =criteria.from(Author.class);

        var authors =  session.createQuery(criteria).list();

     authors.forEach(System.out::println);
        //

    }

    @Test
    void findByOne(){
        insertData();
        var session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        var criteria =  builder.createQuery(Author.class);
        var authors = criteria.from(Author.class);

        criteria.select(authors)
                .where(builder.equal(authors.get("id"), "1")
        );


        Author author = session.createQuery(criteria).getSingleResult();

        System.out.println(author);
    }

    @Test
    void nameByEmailLike(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        var criteria =  builder.createQuery(Author.class);
        var authors = criteria.from(Author.class);

        criteria.select(authors)
                .where(builder.like(authors.get("email"), "%company2.com")
        );

        var authorsEmail = session.createQuery(criteria).list();
        authorsEmail.forEach(System.out::println);

    }

    @Test
    void nameByPiceGreatherThanEquals(){
    insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        var criteria =  builder.createQuery(Book.class);
        var books = criteria.from(Book.class);

        criteria.select(books)
                .where(builder.greaterThanOrEqualTo(books.get("price"), 29.99)
                );

        var booksPrice = session.createQuery(criteria).getResultList();
        booksPrice.forEach(System.out::println);

    }

    @Test
    void findBetween(){
    insertData();
            Session session = HibernateUtil.getSessionFactory().openSession();

            var builder = session.getCriteriaBuilder();
            var criteria =  builder.createQuery(Author.class);
            var root = criteria.from(Author.class);

            criteria.select(root)
                    .where(builder.between(
                            root.get("birthDate"),
                            LocalDate.of(1989,1,1),
                            LocalDate.of(1996,1,1))
                    );

            var result = session.createQuery(criteria).getResultList();
            result.forEach(System.out::println);
    }
    @Test
    void findByMultipleWhere(){
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        var criteria =  builder.createQuery(Book.class);
        var root = criteria.from(Book.class);

        criteria.select(root)
                .where(builder.and(
                        builder.equal(root.get("published"), true),
                        builder.greaterThanOrEqualTo(root.get("price"), 29.99))
                );

        var result = session.createQuery(criteria).getResultList();
        result.forEach(System.out::println);
    }

    @Test
    void findByMultipleSelect(){
        insertData();
        Session session = HibernateUtil.getSessionFactory().openSession();

        var builder = session.getCriteriaBuilder();
        var criteria =  builder.createQuery(Object[].class);
        var root = criteria.from(Book.class);

        criteria.multiselect(root.get("title"), root.get("price"));

        List<Object[]> results = session.createQuery(criteria).getResultList();

       for (Object[] result : results) {
              System.out.println("Title: " + result[0] + " Price: " + result[1]);
       }
    }


    void insertData() {

        var session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var author1 = new Author("author1", "author1@company1.com" , LocalDate.of(1990, 1, 1));
        var author2 = new Author("author2", "author2@company1.com", LocalDate.of(1995, 2, 1));
        var author3 = new Author("author3", "author3@company2.com", LocalDate.of(1998, 3, 1));
        var author4 = new Author("author4", "author@company2", LocalDate.of(1999, 4, 1));


        session.persist(author1);
        session.persist(author2);
        session.persist(author3);
        session.persist(author4);

        var book1 = new Book("book1", 19.99, 450, true, author1);
        var book2 = new Book("book2", 29.99, 350, true, author1);
        var book3 = new Book("book3", 39.99, 250, true, author2);


        session.persist(book1);
        session.persist(book2);
        session.persist(book3);

        session.getTransaction().commit();


    }

}
