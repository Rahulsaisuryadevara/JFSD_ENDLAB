package hibernate.cfg;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import ch.qos.logback.core.net.server.Client;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        // Configure Hibernate
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Client.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        // Insert Records
        Client client1 = new Client();
        client1.setName("RAHUL");
        client1.setGender("Male");
        client1.setAge(28);
        client1.setLocation("New York");
        client1.setEmailAddress("rahul@example.com");
        client1.setNumber("1234567890");
        session.save(client1);

        Client client2 = new Client();
        client2.setName("SHETTY");
        client2.setGender("Female");
        client2.setAge(25);
        client2.setLocation("INDIA");
        client2.setEmailAddress("shetty@example.com");
        client2.setNumber("0987654321");
        session.save(client2);

        // Print All Records
        List<Client> clients = session.createQuery("from Client", Client.class).list();
        for (Client client : clients) {
            System.out.println("ID: " + client.getId());
            System.out.println("Name: " + client.getName());
            System.out.println("Gender: " + client.getGender());
            System.out.println("Age: " + client.getAge());
            System.out.println("Location: " + client.getLocation());
            System.out.println("Email: " + client.getEmailAddress());
            System.out.println("Number: " + client.getNumber());
            System.out.println("-----------------------------");
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}