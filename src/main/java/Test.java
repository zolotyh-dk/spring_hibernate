import com.zolotyh.hibernate_test.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Employee.class)
                .buildSessionFactory();
        try {
            Session session = factory.getCurrentSession();
            Employee employee = new Employee("Дмитрий", "Золотых", "IT", 500);
            session.beginTransaction();
            session.save(employee);
            session.getTransaction().commit();
        }
        finally {
            factory.close();
        }
    }
}
