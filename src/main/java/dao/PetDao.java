package dao;


import entity.Pet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class PetDao {
    private SessionFactory factory;
    private Session session;
    private CriteriaBuilder cb;

    public PetDao() {
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Pet.class)
                .buildSessionFactory();
        session = factory.openSession();
        cb = session.getCriteriaBuilder();
    }
    public Pet getOneById(int id){
        session.getTransaction().begin();
        Pet pet=session.find(Pet.class,id);
        session.getTransaction().commit();
        return pet;
    }

    public List<Pet> getAll() {
        List<Pet> pets = new ArrayList<Pet>();
        session.getTransaction().begin();
        pets = session.createQuery("from Pet", Pet.class).list();
        session.getTransaction().commit();
        return pets;
    }

    public List<Pet> getAllOfKind(String kind){
        List<Pet> pets = new ArrayList<Pet>();
        session.getTransaction().begin();
        CriteriaQuery<Pet> cq=cb.createQuery(Pet.class);
        Root<Pet> root= cq.from(Pet.class);
        Predicate predicate = cb.equal(root.get("kind"), kind.toLowerCase());

        cq.where(predicate);
        pets= (ArrayList<Pet>) session.createQuery(cq).list();

        session.getTransaction().commit();
        return pets;

    }


    public List<Pet> getBetweenAge(int from, int to) {
        List<Pet> pets = new ArrayList<Pet>();
        session.getTransaction().begin();
        CriteriaQuery<Pet> cq=cb.createQuery(Pet.class);
        Root<Pet> root= cq.from(Pet.class);
        Predicate predicate = cb.between(root.<Integer>get("age"), from,to);

        cq.where(predicate);
        pets= (ArrayList<Pet>) session.createQuery(cq).list();

        session.getTransaction().commit();
        return pets;
    }

    public void addAll(List<Pet> pets) {
        session.getTransaction().begin();
        for (Pet pet : pets) {
            session.save(pet);

        }
        session.getTransaction().commit();
    }


    public void close() {
        session.close();
        factory.close();
    }

}
