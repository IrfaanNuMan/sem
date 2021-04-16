package com.napier.sem;
/*
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;*/
import java.sql.*;
import java.util.ArrayList;

/**
 *
 */
public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // Create new Application
        App a = new App();
        // Connect to database
        a.connect();
        // Get Employee
        // Employee emp = a.getEmployee(255530);
        // Display results
        // a.displayEmployee(emp);
        // a.worldpopulation();


//        a.printSalaryReport();
//        a.printSalaryReportByDept("d005");
        ArrayList<City> cy = a.getPopDesc();

//        ArrayList<Employee> employees = a.getAllSalaries();
        // a.displayCity(City);
        a.printreport(cy);
//        a.printSalaries(employees);

//        ArrayList<Employee> employees = a.getAllSalaries("Manager");
        //       a.printSalaries(employees);

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

    /**
     * Gets all the current employees and salaries.
     *
     * @return A list of all employees and salaries, or null if there is an error.
     */
    public <displayArraylist> ArrayList<City> getPopDesc()
    // public ArrayList<Employee> getAllSalaries()
    {
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    /*"SELECT ID,Name, CountryCode, District, Population "
                            + "FROM city order by population desc;";*/

           "SELECT city.name, countrycode, district, city.population "
           + " FROM city,country "
            + "WHERE city.CountryCode = country.Code AND country.Continent = 'Africa' "
            +"order by city.population desc; ";


                    /*"SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC;"; */
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cy = new ArrayList<City>();
            //ArrayList<Employee> employees = new ArrayList<Employee>();
            while (rset.next()) {

                City city = new City();
                city.ID = rset.getInt("ID");
                city.Name = rset.getString("Name");
                city.CountryCode = rset.getString("CountryCode");
                city.Population = rset.getInt("Population");
                /*Employee emp = new Employee();
                emp.emp_no = rset.getInt("employees.emp_no");
                emp.first_name = rset.getString("employees.first_name");
                emp.last_name = rset.getString("employees.last_name");
                emp.salary = rset.getInt("salaries.salary");*/
                cy.add(city);
                //employees.add(emp);


            }
            return cy;

            //return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
            return null;
        }
    }

    /* public void displayCity(City cy)*/
    /* public void displayEmployee(Employee emp) */   /*{
       if (cy != null) {
            System.out.println(
                    cy.ID + " "
                            + cy.Name + " "
                            + cy.CountryCode + " "
                            + cy.District + " "
                            + cy.Population + "\n");*/
                    /*emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");*/
/*

        }
    }*/


    /**
     * Prints a list of employees.
     *
 //    * @param employees The list of employees to print.
     */
    public void printreport(ArrayList<City> cy)
    /*public void printSalaries(ArrayList<Employee> employees)*/ {
        // Print header
        System.out.println(String.format("%-10s %-15s %-20s %-8s %-15s", "ID", "Name", "Country Code", "District", "Population"));
        // System.out.println(String.format("%-10s %-15s %-20s %-8s %-15s" , "Emp No", "First Name", "Last Name", "Salary", "Title"));
        // Loop over all employees in the list
        // for (Employee emp : employees)
        for (City city : cy) {
            String city_string =
                    //String emp_string =
                    String.format("%-10s %-15s %-20s %-8s %-15s",
                            city.ID, city.Name, city.CountryCode, city.District, city.Population);
            // emp.emp_no, emp.first_name, emp.last_name, emp.salary, emp.title);
            System.out.println(city_string);
//end class
        }
    }
}