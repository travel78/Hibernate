package run;

import entity.Product;
import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                                        .configure()
                                        .addAnnotatedClass(User.class)
                                        .addAnnotatedClass(Product.class)
                                        .buildSessionFactory();


        Session session = factory.openSession();

//        session.getTransaction().begin();
//        Product product1 = new Product("milk");
//        Product product2 = new Product("water");
//        List<Product> products = new ArrayList<Product>();
//        products.add(product1);
//        products.add(product2);
//        User user = new User("Peta", "111");
//        user.setProducts(products);
//        session.save(user);
//        Product product3 = new Product("kokos", new User("hhh", "333"));

        session.beginTransaction();
//        session.save(product3);
//        session.save(new User("vasa", "222"));

//        List<User> list = session.createQuery("select u from User u", User.class).list();


        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> q = cb.createQuery(User.class);
        Root<User> from = q.from(User.class);
        Predicate predicate = cb.equal(from.get("password"), "1111");
        Predicate predicate1 = cb.equal(from.get("name"), "1fsf1");
        session.getTransaction().commit();
        Predicate and = cb.and(predicate, predicate1);
        q.where(and);
        session.createQuery(q);



        session.close();

        factory.close();

    }
}
