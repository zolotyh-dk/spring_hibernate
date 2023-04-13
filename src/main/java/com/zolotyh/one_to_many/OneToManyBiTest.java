package com.zolotyh.one_to_many;

import com.zolotyh.one_to_many.entity.Department;
import com.zolotyh.one_to_many.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class OneToManyBiTest {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(Department.class)
                .buildSessionFactory();
        Session session = null;
        Department department = new Department("IT", 1000, 300);
        Employee employee1 = new Employee("Дмитрий", "Золотых", 500);
        Employee employee2 = new Employee("Александра", "Смирнова", 650);
        department.addEmployeeToDepartment(employee1);
        department.addEmployeeToDepartment(employee2);

        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.save(department);
            Employee emp = session.get(Employee.class, 2);
            session.delete(emp);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}
