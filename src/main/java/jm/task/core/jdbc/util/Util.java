package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.rmi.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    public Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "nikita";
        String password = "nikita";

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public SessionFactory getSessionFactory() {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(java.util.logging.Level.SEVERE);
        try {
            Properties props = new Properties();
            props.put("hibernate.connection.driver_class", "org.postgresql.Driver");
            props.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
            props.put("hibernate.connection.username", "nikita");
            props.put("hibernate.connection.password", "nikita");

            props.put("hibernate.hbm2ddl.auto", "update");

            Configuration configuration = new Configuration();
            configuration.setProperties(props);
            configuration.addAnnotatedClass(User.class);

            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            return configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
