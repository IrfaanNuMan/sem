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
        //Prints all cities in the world descending
        ArrayList<City> cy = a.getPop();
        a.printreport(cy);

        //Prints the top N populated cities in the world
        ArrayList<City> cy1 = a.topNPopCitiesInWorld();
        a.printTopNPopCitiesInWorld(cy1);

        //Prints the top N populated cities in a continent
        ArrayList<City> cy2 = a.topNPopulatedCitiesContinent();
        a.printtopNPopulatedCitiesContinent(cy2);

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
            String strSelect = "SELECT  ID, name, countrycode, district, population "
            + "FROM city order by population desc limit 10;";



            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cy = new ArrayList<City>();

            while (rset.next())
            {

                City city = new City();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("NAME");
                city.CountryCode = rset.getString("COUNTRYCODE");
                city.District = rset.getString("DISTRICT");
                city.Population = rset.getInt("POPULATION");
                cy.add(city);

            }

            return cy;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
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

        System.out.println(String.format("All the cities in the world population descending order \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy)

        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }

/***
 * ########################################################
 * TOP N CITIES IN WORLD DESCENDING
 * ##################################################3#####
 */

public ArrayList<City> topNPopCitiesInWorld()
{
    try
    {
        // Create an SQL statement
        Statement stmt = con.createStatement();
        // Create string for SQL statement
        String strSelect = "SELECT ID ,city.name, countrycode, district, city.population "
                + "FROM city,country "
                + "WHERE city.CountryCode = country.Code AND country.Continent = 'Africa' "
                + "order by city.population desc limit 10;";



        ResultSet rset = stmt.executeQuery(strSelect);

        ArrayList<City> cy = new ArrayList<City>();

        while (rset.next())
        {

            City city = new City();
            city.ID = rset.getInt("ID");
            city.Name = rset.getString("NAME");
            city.CountryCode = rset.getString("COUNTRYCODE");
            city.District = rset.getString("DISTRICT");
            city.Population = rset.getInt("POPULATION");
            cy.add(city);

        }

        return cy;

    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
        System.out.println("Failed to get City details");
        return null;
    }
}

    /***
     *
     * @param cy1
     */


    public void printTopNPopCitiesInWorld(ArrayList<City> cy1)
    {
        // Print header

        System.out.println(String.format( "The top N populated cities in the world \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy1)

        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }

    /***
     *
     * ###########################################
     * THE TOP N POPULATED CITIES IN A CONTINENT
     * ###########################################
     */

    public ArrayList<City> topNPopulatedCitiesContinent()
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID ,city.name, countrycode, district, city.population "
                    + "FROM city,country "
                    + "WHERE city.CountryCode = country.Code AND country.Continent = 'Africa' "
                    + "order by city.population desc limit 10;";



            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cy2 = new ArrayList<City>();

            while (rset.next())
            {

                City city = new City();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("NAME");
                city.CountryCode = rset.getString("COUNTRYCODE");
                city.District = rset.getString("DISTRICT");
                city.Population = rset.getInt("POPULATION");
                cy2.add(city);

            }

            return cy2;

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /***
     *
     * @param cy2
     */


    public void printtopNPopulatedCitiesContinent(ArrayList<City> cy2)
    {
        // Print header

        System.out.println(String.format(" The top N populated cities in a continent \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy2)

        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }



}


