package com.zolotyh.hibernate_test;

import com.zolotyh.hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Test3 {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            session.beginTransaction();
            List<Employee> employees = session.createQuery("FROM Employee").getResultList();
            for (Employee e : employees) {
                System.out.println(e);
            }
            List<Employee> employees1 = session.createQuery("FROM Employee WHERE department = 'IT' AND salary > 501")
                    .getResultList();
            for (Employee e : employees1) {
                System.out.println(e);
            }
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
