package com.zolotyh.hibernate_test;

import com.zolotyh.hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test4 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            Employee employee = session.get(Employee.class, 1);
            employee.setSalary(1000);

            session.createQuery("UPDATE Employee SET salary = 500 WHERE department = 'Гидравлик'").executeUpdate();
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
