package com.skillnext1;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            Student student = new Student(
                    "Rahul",
                    "rahul@gmail.com",
                    21
            );

            session.save(student);

            tx.commit();
            System.out.println("Student inserted successfully!");

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            HibernateUtil.getSessionFactory().close();
        }
    }
}

