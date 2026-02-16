package com.example.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.example.entity.Users;
import com.example.util.HibernateUtil;

public class LoginDAO 
{

    public static boolean validate(String username, String password) 
	{

        try 
		{
			Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Users> q = session.createQuery(
                "FROM Users WHERE username=:u AND password=:p", Users.class);
            q.setParameter("u", username);
            q.setParameter("p", password);

            return q.uniqueResult() != null;
        }
		catch(Exception e)
		{
			return false;
		}
    }
}