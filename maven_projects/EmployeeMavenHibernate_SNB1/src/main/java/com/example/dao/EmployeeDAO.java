package com.example.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.entity.Employee;
import com.example.util.HibernateUtil;

public class EmployeeDAO {

    // CREATE
    public static void insert(Employee emp) 
	{
        Transaction tx = null;
        try  
		{
			Session s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            s.persist(emp);
            tx.commit();
            System.out.println("Employee inserted");
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    // READ ALL
    public static void getAll() 
	{
        try  
		{
			Session s = HibernateUtil.getSessionFactory().openSession();
            List<Employee> list = s.createQuery("FROM Employee", Employee.class).list();
            list.forEach(e ->
             System.out.println(e.getId()+" "+e.getName()+" "+e.getDepartment()+" "+e.getSalary())
            );
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    // READ BY ID
    public static void getById(int id) {
        try  {
			Session s = HibernateUtil.getSessionFactory().openSession();
            Employee e = s.get(Employee.class, id);
            System.out.println(e != null ? e.getName() : "Employee not found");
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
    }

    // UPDATE
    public static void update(Employee emp) {
        Transaction tx = null;
        try  {
			Session s = HibernateUtil.getSessionFactory().openSession();
            tx = s.beginTransaction();
            s.merge(emp);
            tx.commit();
            System.out.println("Employee updated");
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

    // DELETE
    public static void delete(int id) {
        Transaction tx = null;
        try  {
			Session s = HibernateUtil.getSessionFactory().openSession();
            Employee e = s.get(Employee.class, id);
            if (e != null) {
                tx = s.beginTransaction();
                s.remove(e);
                tx.commit();
                System.out.println("Employee deleted");
            }
        }
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }
    // COUNT BY DEPARTMENT
    public static void countByDepartment(String dept) {
    try (Session s = HibernateUtil.getSessionFactory().openSession()) {

        Long count = s.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.department = :dept", Long.class)
                .setParameter("dept", dept)
                .uniqueResult();

        System.out.println("Count in " + dept + " : " + count);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}

