package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    static final Util util = new Util();
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        System.out.println("Table users is ready");
    }

    @Override
    public void dropUsersTable() {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery("drop table if exists users").executeUpdate();
            tx.commit();
            System.out.println("Table users dropped");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            tx.commit();
            System.out.println("User has been saved");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.remove(user);
                System.out.printf("User with id %d has been removed", id);
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.createMutationQuery("delete from User").executeUpdate();
            tx.commit();
            System.out.println("Table ");
        }
    }
}
