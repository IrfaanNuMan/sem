package com.napier.sem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppIntegrationTest {

    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060");
    }

    @Test
    void testPrintAllCapCitiesInWorldDesc(ArrayList < City > cy5) {

     /*
        City city = app.printAllCapCitiesInWorldDesc ( );
        assertEquals(city.ID,2331 );
        assertEquals(city.Population,9981619 );
        assertEquals(city.CountryCode,"KOR");*/



    }
}