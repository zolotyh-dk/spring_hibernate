package com.zolotyh.one_to_one;

import com.zolotyh.one_to_one.entity.Detail;
import com.zolotyh.one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SaveTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            Employee employee = new Employee("Дмитрий", "Золотых", "IT", 500);
            Detail detail = new Detail("Старый Оскол", "123456789", "zolotyh@gmail.com");
            employee.setEmpDetail(detail);
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
