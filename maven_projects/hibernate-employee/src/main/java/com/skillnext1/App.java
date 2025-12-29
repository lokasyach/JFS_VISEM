package com.skillnext1;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== EMPLOYEE MANAGEMENT MENU =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Delete Employee");
            System.out.println("4. View Employees Ordered by Name");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addEmployee(sc);
                    break;
                case 2:
                    viewEmployees();
                    break;
                case 3:
                    deleteEmployee(sc);
                    break;
                case 4:
                    viewEmployeesOrderedByName();
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

    // -------- ADD EMPLOYEE --------
    private static void addEmployee(Scanner sc) {

        System.out.print("Enter name: ");
        String name = sc.next();

        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        System.out.print("Enter department: ");
        String dept = sc.next();

        Employee emp = new Employee(name, salary, dept);

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(emp);

        tx.commit();
        session.close();

        System.out.println("Employee added successfully!");
    }

    // -------- VIEW ALL --------
    private static void viewEmployees() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> employees =
                session.createQuery("FROM Employee", Employee.class).list();

        System.out.println("\nID | NAME | SALARY | DEPT");
        for (Employee e : employees) {
            System.out.println(
                    e.getId() + " | " +
                    e.getName() + " | " +
                    e.getSalary() + " | " +
                    e.getDept()
            );
        }

        session.close();
    }

    // -------- DELETE --------
    private static void deleteEmployee(Scanner sc) {

        System.out.print("Enter employee ID to delete: ");
        int id = sc.nextInt();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee emp = session.get(Employee.class, id);

        if (emp != null) {
            session.delete(emp);
            tx.commit();
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
            tx.rollback();
        }

        session.close();
    }

    // -------- ORDER BY NAME --------
    private static void viewEmployeesOrderedByName() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Employee> query =
                session.createQuery(
                        "FROM Employee ORDER BY name",
                        Employee.class
                );

        List<Employee> employees = query.list();

        System.out.println("\nID | NAME | SALARY | DEPT");
        for (Employee e : employees) {
            System.out.println(
                    e.getId() + " | " +
                    e.getName() + " | " +
                    e.getSalary() + " | " +
                    e.getDept()
            );
        }

        session.close();
    }
}
