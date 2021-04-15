package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class App {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();

        a.worldpopulation();

        // Disconnect from database
        a.disconnect();
    } // end main

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect() {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {
                // Wait a bit for db to start needed for travis but can be removed locally if db running
                Thread.sleep(0);

                // Connect to database locally
               // con = DriverManager.getConnection("jdbc:mysql://localhost:33060/employees?useSSL=true", "root", "example");

                // Connect to database inside docker
               con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");

                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect() {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }
    public City worldpopulation()
    //public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect ="SELECT ID, Name, CountryCode, District, Population "
                            + "FROM city order by population desc;";



            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new City valid.
            // Check one is returned
            if (rset.next())
            {
                City cy = new City ();

                cy.ID = rset.getInt("ID");
                cy.Name = rset.getString("Name");
                cy.CountryCode = rset.getString("CountryCode");
                cy.District = rset.getString("District");
                cy.Population = rset.getInt("Population");

                displayCity(cy);
                return cy;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }
    public void displayCity(City cy)

    {
        if (cy != null)
        {
            System.out.println(
                    cy.ID + " "
                            + cy.Name + " "
                            + cy.CountryCode + " "
                            + cy.District + " "
                            + cy.Population + "\n");

        }
    }


} //end class