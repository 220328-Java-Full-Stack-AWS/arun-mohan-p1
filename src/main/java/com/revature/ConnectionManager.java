package com.revature;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    // singleton - private constructor that cannot be invoked elsewhere
    private static Connection connection;

    private ConnectionManager() {

    }


    public static Connection getConnection() {
        // if there is no connection, create one
        if (connection == null) {
            connection = connect();
        }

        return connection;
    }

    public static void close() {
        // close connection if it exists and set to null
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Problem occurred while attempting to close database connection.");
        }
        connection = null;
    }

    public static Connection connect() {
        try {
            Class.forName("org.postgresql.Driver");
            // algorithm for processing application.properties file for building connection string
            Properties properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream input = loader.getResourceAsStream("application.properties");
            properties.load(input);

            // build connection string
            String connectionString = "jdbc:postgresql://" +
                    properties.getProperty("hostname") + ":" +
                    properties.getProperty("port") + "/" +
                    properties.getProperty("dbname");

            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(connectionString, username, password);



        } catch (IOException | SQLException e) {
            System.out.println("Database Connection failed, might be application.properties file.");
        } catch (ClassNotFoundException e) {
            System.out.println("Database Connection failed, might be application.properties file.");
        }

        return connection;


    }



}
