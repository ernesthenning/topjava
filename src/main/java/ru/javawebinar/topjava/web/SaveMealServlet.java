package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DataAccessUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveMealServlet extends HttpServlet {
    DataAccessUtils dau = new DataAccessUtils();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/saveMeal.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateTimeStr = (String) request.getParameter("dateTime");
        String caloriesStr = (String) request.getParameter("calories");
        String description = (String) request.getParameter("description");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        int calories = Integer.parseInt(caloriesStr);
        Meal meal = new Meal(dateTime, description, calories);
        dau.saveMeal(meal);
        request.getRequestDispatcher("/meals").forward(request, response);
    }

}
