package com.infoshareacademy.workshop.utils;

import com.infoshareacademy.workshop.model.Todo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DbInit implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        /*var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();
        session.save(new Todo("Nauczyć się Javy"));
        session.save(new Todo("Nauczyć się C#"));
        transaction.commit();
        session.close();*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateUtil.getSessionFactory().close();
    }
}
