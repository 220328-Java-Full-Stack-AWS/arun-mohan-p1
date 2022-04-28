package com.revature.servlets;

import com.revature.ConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;

public class DependencyLoaderListener implements ServletContextListener {

    Connection conn;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        conn = ConnectionManager.getConnection();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionManager.close();
    }



}
