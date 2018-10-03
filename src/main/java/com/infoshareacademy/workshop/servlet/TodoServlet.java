package com.infoshareacademy.workshop.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshareacademy.workshop.service.TodoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(name = "TodoServlet", urlPatterns = {"/api/todos/*"})
public class TodoServlet extends HttpServlet {
    private final TodoService service = new TodoService();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(resp.getOutputStream(), service.getAllTodos());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var text = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(resp.getOutputStream(), service.addTodo(text));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var pathInfo = req.getPathInfo(); // np. "/123" dla "localhost:8080/api/todos/123"
        int id = Integer.valueOf(pathInfo.substring(1));
        resp.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(resp.getOutputStream(), service.toggleTodo(id));
    }
}
