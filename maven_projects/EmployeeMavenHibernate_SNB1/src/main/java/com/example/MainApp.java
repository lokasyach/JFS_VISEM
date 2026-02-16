//MainApp.java (Menu Driven)
package com.example;

import java.util.Scanner;

import org.hibernate.boot.model.internal.XMLContext;

import com.example.dao.EmployeeDAO;
import com.example.dao.LoginDAO;
import com.example.entity.Employee;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Username: ");
        String u = sc.next();

        System.out.print("Password: ");
        String p = sc.next();

        if (!LoginDAO.validate(u, p)) {
            System.out.println("Invalid credentials");
            return;
        }

        System.out.println("Login Successful");

        int ch;
        do {
            System.out.println("\n1.Insert 2.View 3.ViewById 4.Update 5.Delete 6.CountbyDept 7.Exit");
            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    Employee e = new Employee();
					System.out.println("Name: ");
                    e.setName(sc.next());
					System.out.println("Dept: ");
                    e.setDepartment(sc.next());
					System.out.println("Salary: ");
                    e.setSalary(sc.nextDouble());
                    EmployeeDAO.insert(e);
                    break;

                case 2:
					System.out.println("List of employees: ");
                    EmployeeDAO.getAll();
                    break;

                case 3:
					System.out.println("Enter id: ");
                    EmployeeDAO.getById(sc.nextInt());
                    break;

                case 4:
                    Employee ue = new Employee();
					System.out.println("id: ");
                    ue.setId(sc.nextInt());
					System.out.println("Name: ");
                    ue.setName(sc.next());
					System.out.println("Dept: ");
                    ue.setDepartment(sc.next());
					System.out.println("Salary: ");
                    ue.setSalary(sc.nextDouble());
                    EmployeeDAO.update(ue);
                    break;

                case 5:
					System.out.println("id: ");
                    EmployeeDAO.delete(sc.nextInt());
                    break;
                
                case 6:
                    System.out.println("Department: ");
                    EmployeeDAO.countByDepartment(sc.next());
                    break;
            }

        } while (ch != 7);

        sc.close();
    }
}