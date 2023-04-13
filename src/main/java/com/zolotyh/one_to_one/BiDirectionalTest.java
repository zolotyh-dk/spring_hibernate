package com.zolotyh.one_to_one;

import com.zolotyh.one_to_one.entity.Detail;
import com.zolotyh.one_to_one.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class BiDirectionalTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Detail.class)
                .buildSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            Employee employee = new Employee("Алексей", "Попов", "Привод", 300);
            Detail detail = new Detail("Старый Оскол", "564789213", "bobsky@gmail.com");
            employee.setEmpDetail(detail);
            detail.setEmployee(employee);
            session.beginTransaction();
            session.save(employee);

            Detail empDetail = session.get(Detail.class, 2);
            System.out.println(empDetail.getEmployee());
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
