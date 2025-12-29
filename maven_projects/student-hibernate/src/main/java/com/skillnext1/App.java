package com.skillnext1;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Delete Student");
            System.out.println("4. View Students Ordered by Name");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    deleteStudent(sc);
                    break;
                case 4:
                    viewStudentsOrderedByName();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        sc.close();
        HibernateUtil.getSessionFactory().close();
    }

    // -------- ADD STUDENT --------
    private static void addStudent(Scanner sc) {

        System.out.print("Enter name: ");
        String name = sc.next();

        System.out.print("Enter semester: ");
        int sem = sc.nextInt();

        System.out.print("Enter department: ");
        String dept = sc.next();

        Student student = new Student(name, sem, dept);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(student);

        tx.commit();
        session.close();

        System.out.println("Student added successfully!");
    }

    // -------- VIEW ALL --------
    private static void viewStudents() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Student> students =
                session.createQuery("FROM Student", Student.class).list();

        System.out.println("\nID | NAME | SEM | DEPT");
        for (Student s : students) {
            System.out.println(
                    s.getId() + " | " +
                    s.getName() + " | " +
                    s.getSem() + " | " +
                    s.getDept()
            );
        }

        session.close();
    }

    // -------- DELETE --------
    private static void deleteStudent(Scanner sc) {

        System.out.print("Enter student ID to delete: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Student student = session.get(Student.class, id);

        if (student != null) {
            session.delete(student);
            tx.commit();
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found!");
            tx.rollback();
        }

        session.close();
    }

    // -------- ORDER BY NAME --------
    private static void viewStudentsOrderedByName() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Student> query =
                session.createQuery(
                        "FROM Student ORDER BY name",
                        Student.class
                );

        List<Student> students = query.list();

        System.out.println("\nID | NAME | SEM | DEPT");
        for (Student s : students) {
            System.out.println(
                    s.getId() + " | " +
                    s.getName() + " | " +
                    s.getSem() + " | " +
                    s.getDept()
            );
        }

        session.close();
    }
}
