package com.skillnext1;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.getWriter().println("<h2>Student Servlet is WORKING</h2>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("=== STUDENT DO POST CALLED ===");

        String name = req.getParameter("name");
        String semStr = req.getParameter("sem");
        String dept = req.getParameter("dept");

        System.out.println("Name : " + name);
        System.out.println("Sem  : " + semStr);
        System.out.println("Dept : " + dept);

        // Validation
        if (name == null || semStr == null || dept == null ||
            name.isEmpty() || semStr.isEmpty() || dept.isEmpty()) {

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing fields");
            return;
        }

        int sem;
        try {
            sem = Integer.parseInt(semStr);
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid semester");
            return;
        }

        // Create Student object
        Student std = new Student();
        std.setName(name);
        std.setSem(sem);
        std.setDept(dept);

        // Save to DB
        StudentDAO.save(std);

        // Redirect to success page
        resp.sendRedirect("success.jsp");
    }
}
