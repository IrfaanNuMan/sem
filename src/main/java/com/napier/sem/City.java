package com.napier.sem;

/*import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException; */
import java.sql.*;
import java.util.ArrayList;


public class City {
    private  int id;
    private  String name;
    private  String countryCode;
    private  String district;
    private int population;
    public Connection con;


    public City () {

    }

    /**
     *
     *City method
     */
    public City(int id, String name, String countryCode, String district, int population) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
        this.district = district;
        this.population = population;

    }


    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", district='" + district + '\'' +
                ", population=" + population +
                '}';
    }

        public ArrayList<City> getPop()
    {
        try
        {
            // Create an SQL statement
            Connection con;
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect = "SELECT name, countryCode, district, population"
                               + "FROM city order by population desc;";

                   /* "SELECT employees.emp_no, employees.first_name, employees.last_name, salaries.salary "
                            + "FROM employees, salaries "
                            + "WHERE employees.emp_no = salaries.emp_no AND salaries.to_date = '9999-01-01' "
                            + "ORDER BY employees.emp_no ASC;";*/
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Extract employee information
            ArrayList<City> cityPop = new ArrayList<City>();
            while (rset.next())
            {
                City cy = new City();
                cy.id = rset.getInt("cityPop.id");
                cy.name = rset.getString("cityPop.name");
                cy.countryCode = rset.getString("cityPop.countryCode");
                cy.district = rset.getString("cityPop.district");
                cy.population = rset.getInt("cityPop.population");

                cityPop.add(cy);
            }
            return cityPop;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city populations details");
            return null;
        }
    }






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
