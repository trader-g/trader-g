package com.bbdgrads.beancards.services;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class DatabaseConnectionTestService {

    private final DataSource dataSource;

    public DatabaseConnectionTestService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean testConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return true; // Connection successful
        } catch (SQLException e) {
            // Log the exception or print it to the console
            e.printStackTrace();
            return false; // Connection failed
        }
    }
}