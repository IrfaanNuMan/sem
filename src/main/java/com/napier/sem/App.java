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
        // Get Employee
       // Employee emp = a.getEmployee(255530);
        // Display results
       // a.displayEmployee(emp);
        a.worldpopulation();



//        a.printSalaryReport();
//        a.printSalaryReportByDept("d005");

//        ArrayList<Employee> employees = a.getAllSalaries();
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
    public City worldpopulation()
    //public Employee getEmployee(int ID)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =/* "SELECT *  FROM city ";*/


                    "SELECT ID, Name, CountryCode, District, Population "
            + "FROM city order by population desc;";


                    /*
                    "SELECT emp_no, first_name, last_name "
                            + "FROM employees "
                            + "WHERE emp_no = " + ID;*/
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new employee if valid.
            // Check one is returned
            if (rset.next())
            {
                City cy = new City ();
                //Employee emp = new Employee();
                cy.ID = rset.getInt("ID");
                cy.Name = rset.getString("Name");
                cy.CountryCode = rset.getString("CountryCode");
                cy.District = rset.getString("District");
                cy.Population = rset.getInt("Population");
              /* emp.emp_no = rset.getInt("emp_no");
                emp.first_name = rset.getString("first_name");
                emp.last_name = rset.getString("last_name");*/
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
   /* public void displayEmployee(Employee emp)*/
    {
        if (cy != null)
        {
            System.out.println(
                    cy.ID + " "
                            + cy.Name + " "
                            + cy.CountryCode + " "
                            + cy.District + " "
                            + cy.Population + "\n");
                    /*emp.emp_no + " "
                            + emp.first_name + " "
                            + emp.last_name + "\n"
                            + emp.title + "\n"
                            + "Salary:" + emp.salary + "\n"
                            + emp.dept_name + "\n"
                            + "Manager: " + emp.manager + "\n");*/
        }
    }


} //end class