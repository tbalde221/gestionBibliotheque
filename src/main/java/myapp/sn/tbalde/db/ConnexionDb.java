package sn.tbalde.db;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnexionDb {

    public Connection getConnection() {

        try {

            Properties properties = new Properties();

            try (FileInputStream fis = new FileInputStream("conf.properties")) {
                properties.load(fis);
            }

            Class.forName(properties.getProperty("jdbc.driver.class"));

            String url = properties.getProperty("jdbc.url");
            String login = properties.getProperty("jdbc.login");
            String password = properties.getProperty("jdbc.password");

            return DriverManager.getConnection(url, login, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}