package run;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                        .configure()
                                        .addAnnotatedClass(User.class)
                                        .buildSessionFactory();


        Session session = factory.openSession();

//        session.getTransaction().begin();

        session.beginTransaction();

        session.getTransaction().commit();


        session.close();

        factory.close();

    }
}
