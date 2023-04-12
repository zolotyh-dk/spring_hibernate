package com.zolotyh.hibernate_test;

import com.zolotyh.hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetByIdTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            Employee employee = new Employee("Виталий", "Сабынин", "Гидравлик", 300);
            session.beginTransaction();
            session.save(employee);
            int id = employee.getId();
            Employee myEmp = session.get(Employee.class, id);
            System.out.println(myEmp);
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
