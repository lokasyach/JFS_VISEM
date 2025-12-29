package com.skillnext1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employee")
public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.getWriter().println("<h2>Employee Servlet is WORKING</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("=== DO POST CALLED ===");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String salaryStr = req.getParameter("salary");

        System.out.println("Name   : " + name);
        System.out.println("Email  : " + email);
        System.out.println("Salary : " + salaryStr);

        if (name == null || email == null || salaryStr == null ||
            name.isEmpty() || email.isEmpty() || salaryStr.isEmpty()) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing fields");
            return;
        }

        double salary;
        try {
            salary = Double.parseDouble(salaryStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid salary");
            return;
        }

        Employee emp = new Employee();
        emp.setName(name);
        emp.setEmail(email);
        emp.setSalary(salary);

        EmployeeDAO.save(emp);

        resp.sendRedirect("success.jsp");
    }
}
