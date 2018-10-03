package com.infoshareacademy.workshop.service;

import com.infoshareacademy.workshop.model.Todo;
import com.infoshareacademy.workshop.utils.HibernateUtil;

import java.util.List;

public class TodoService {
    public List<Todo> getAllTodos() {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Todo.class).list();
    }

    public Todo addTodo(String todoText) {
        var todo = new Todo(todoText);
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.persist(todo);
        transaction.commit();
        session.close();
        return todo;
    }

    public Todo toggleTodo(int id) {
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        var todo = (Todo) session.get(Todo.class, id);
        todo.setDone(!todo.isDone());
        transaction.commit();
        session.close();
        return todo;
    }
}
