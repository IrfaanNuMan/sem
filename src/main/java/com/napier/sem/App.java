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
     * @param args main application connection
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Connect to database
        if (args.length < 1)
        {
            a.connect("localhost:3306");
        }
        else
        {
            a.connect(args[0]);
        }



/***
 * methods to get and print reports
 */
        //Prints all cities in the world descending
        ArrayList<City> cy = a.getPop();
        a.printreport(cy);

        //Prints the top N populated cities in the world
       /* ArrayList<City> cy1 = a.topNPopCitiesInWorld();
        a.printTopNPopCitiesInWorld(cy1);*/

        //Prints the top N populated cities in a continent
        ArrayList<City> cy2 = a.topNPopulatedCitiesContinent();
        a.printtopNPopulatedCitiesContinent(cy2);

        //prints the top N populated cities in a region
        ArrayList<City> cy3 = a.topNPopulatedCitiesRegion();
        a.printTopNPopulatedCitiesRegion(cy3);

        //prints all capital cities in a continent descending
        ArrayList<City> cy4 = a.allCapCitiesContinentDesc();
        a.printAllCapCitiesContinentDesc(cy4);

        //prints all cities in world descending
        ArrayList<City> cy5 = a.allCapCitiesInWorldDesc();
        a.printAllCapCitiesInWorldDesc(cy5);
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
    public void connect(String location) {
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
                 con = DriverManager.getConnection("jdbc:mysql://localhost:33060/world?useSSL=true", "root", "example");

                // Connect to database inside docker
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                //con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?useSSL=false", "root", "example");
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
     *##########################################################
     * @return --THE TOP N POPULATED CITIES IN THE WORLD
     * #########################################################
     */

    public ArrayList<City> getPop() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT  ID, name, countrycode, district, population "
                    + "FROM city order by population desc limit 10;";


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cy = new ArrayList<City>();

            while (rset.next()) {

                City city = new City();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("NAME");
                city.CountryCode = rset.getString("COUNTRYCODE");
                city.District = rset.getString("DISTRICT");
                city.Population = rset.getInt("POPULATION");
                cy.add(city);

            }

            return cy;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /***
     *
     * @param cy  method to  print report
     */


    public void printreport(ArrayList<City> cy) {
        // Print header

        System.out.println(String.format("THE TOP N POPULATED CITIES IN THE WORLD \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy) {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }

/***
 * ########################################################
 * TOP N POPULATED CITIES IN A CONTINENT
 * ##################################################3#####
 */
/*
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
       // a.connect();


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

/*
    public void printTopNPopCitiesInWorld(ArrayList<City> cy1)
    {
        // Print header

        System.out.println(String.format( "TOP N POPULATED CITIES IN A CONTINENT \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy1)

        {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }*/

    /***
     *
     * ###########################################
     * THE TOP N POPULATED CITIES IN A CONTINENT
     * ###########################################
     */

    public ArrayList<City> topNPopulatedCitiesContinent() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID ,city.name, countrycode, district, city.population "
                    + "FROM city,country "
                    + "WHERE city.CountryCode = country.Code AND country.Continent = 'Africa' "
                    + "order by city.population desc limit 10;";


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cy2 = new ArrayList<City>();

            while (rset.next()) {

                City city = new City();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("NAME");
                city.CountryCode = rset.getString("COUNTRYCODE");
                city.District = rset.getString("DISTRICT");
                city.Population = rset.getInt("POPULATION");
                cy2.add(city);

            }

            return cy2;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /***
     *
     * @param cy2 method to print top  N population of cities in a continent
     */


    public void printtopNPopulatedCitiesContinent(ArrayList<City> cy2) {
        // Print header

        System.out.println(String.format(" THE TOP N POPULATED CITIES IN A CONTINENT \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy2) {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }

    /***
     *#######################################################
     * THE TOP N POPULATED CITIES IN A REGION
     *#####################################################
     */

    public ArrayList<City> topNPopulatedCitiesRegion() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID, city.name, countrycode, district, city.population "
                    + "FROM city,country "
                    + "WHERE city.CountryCode = country.Code AND country.Region = 'Caribbean' "
                    + "order by city.population desc limit 10; ";


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cy3 = new ArrayList<City>();

            while (rset.next()) {

                City city = new City();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("NAME");
                city.CountryCode = rset.getString("COUNTRYCODE");
                city.District = rset.getString("DISTRICT");
                city.Population = rset.getInt("POPULATION");
                cy3.add(city);

            }

            return cy3;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /***
     *
     * @param cy3 method to print top N population of  cities in a region
     */


    public void printTopNPopulatedCitiesRegion(ArrayList<City> cy3) {
        // Print header

        System.out.println(String.format(" THE TOP N POPULATED CITIES IN A REGION \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy3) {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }

    /***
     *#######################################################
     * ALL CAPITAL CITIES IN A CONTINENT DESCENDING
     *#####################################################
     */

    public ArrayList<City> allCapCitiesContinentDesc() {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT ID, countrycode, district, city.name, country.name, city.population "
                    + "FROM city, country "
                    + "WHERE city.ID = country.Capital AND country.Continent = 'Asia' "
                    + "order by city.population desc; ";


            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<City> cy4 = new ArrayList<City>();

            while (rset.next()) {

                City city = new City();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("NAME");
                city.CountryCode = rset.getString("COUNTRYCODE");
                city.District = rset.getString("DISTRICT");
                city.Population = rset.getInt("POPULATION");
                cy4.add(city);

            }

            return cy4;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /***
     *
     * @param cy4  method to  print all capital cities in a continent in descending order
     */


    public void printAllCapCitiesContinentDesc(ArrayList<City> cy4) {
        // Print header

        System.out.println(String.format(" ALL CAPITAL CITIES IN A CONTINENT DESCENDING \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

        // Loop over all cities in the list
        for (City city : cy4) {
            String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);

            System.out.println(emp_string);
        }
    }

        /***
         *#######################################################
         * ALL CITIES IN THE WORLD DESCENDING
         *#####################################################
         */

        public ArrayList<City> allCapCitiesInWorldDesc ()
        {
            try {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Create string for SQL statement
                String strSelect = "SELECT ID, name, countrycode, district, population "
                        + "FROM city order by population desc; ";


                ResultSet rset = stmt.executeQuery(strSelect);

                ArrayList<City> cy5 = new ArrayList<City>();

                while (rset.next()) {

                    City city = new City();
                    city.ID = rset.getInt("ID");
                    city.Name = rset.getString("NAME");
                    city.CountryCode = rset.getString("COUNTRYCODE");
                    city.District = rset.getString("DISTRICT");
                    city.Population = rset.getInt("POPULATION");
                    cy5.add(city);

                }

                return cy5;

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Failed to get City details");
                return null;
            }
        }

        /***
         *
         * @param cy5  method to  print all capital cities inthe  world in descending order
         */


        public void printAllCapCitiesInWorldDesc (ArrayList < City > cy5)
        {
            // Check employees is not null
            if (cy5 == null)
            {
                System.out.println("No City");
                return;
            }
            // Print header

            System.out.println(String.format(" ALL CITIES IN THE WORLD DESCENDING \n" + "%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));

            // Loop over all cities in the list
            for (City city : cy5) {
                if (city == null)
                    continue;
                String emp_string =
                        String.format("%-10s %-15s %-20s %-8s %-15s",
                                city.ID, city.Name, city.CountryCode, city.District, city.Population);

                System.out.println(emp_string);
            }
        }
    }






