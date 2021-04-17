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


/***
 * method to get all city's population and print the report
 */
        ArrayList<City> cy = a.getPop();
        a.printreport(cy);



        // Disconnect from database
        a.disconnect();
    }

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


    /***
     *
     * @return -- All the cities in the world organised by largest population to smallest.
     */

    public ArrayList<City> getPop()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID, Name, CountryCode, District, Population "
                    + "FROM city order by population desc;";


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cy = new ArrayList<City>();

            while (rset.next())
            {

                City city = new City();
                city.id = rset.getInt("ID");
                city.name = rset.getString("NAME");
                city.countryCode = rset.getString("COUNTRYCODE");
                city.district = rset.getString("DISTRICT");
                city.population = rset.getInt("POPULATION");
                cy.add(city);

            }

            return cy;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get salary details");
            return null;
        }
    }

    /***
     *
     * @param cy
     */


    public void printreport(ArrayList<City> cy)
    {
        // Print header

        System.out.println(String.format("%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy)

        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.id, city.name, city.countryCode, city.district, city.population);

            System.out.println(emp_string);
        }
    }
}