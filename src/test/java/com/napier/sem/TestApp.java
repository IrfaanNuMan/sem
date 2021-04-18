package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


public class TestApp {
    static App app;
    private Object ArrayList;

    @BeforeAll
    static void init()
    {
        app = new App();
    }
    @Test
    void printAllCapCitiesInWorldDescTestNull() {
        app.printAllCapCitiesInWorldDesc(null);
    }
    @Test
    void printAllCapCitiesInWorldDescTestEmpty() {
        app.printAllCapCitiesInWorldDesc(null);
    }
    @Test
    void printAllCapCitiesInWorldDescTestConNull() {
        ArrayList<City> cy5 = new ArrayList<City>();
        cy5.add(null);
        app.printAllCapCitiesInWorldDesc(null);
    }
    @Test
    void printAllCapCitiesInWorldDesc() {
        ArrayList<City> cy5 = new ArrayList<City>();
        City city = new City();
        city.ID = 2331;
       /* city.Name = Seoul;
        city.CountryCode = KOR;
        city.District = Seoul;*/
        city.Population = 9981619;
        cy5.add(city);
        app.printAllCapCitiesInWorldDesc(cy5);

    }
    }




