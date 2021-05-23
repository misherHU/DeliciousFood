package org.example;

import static org.junit.Assert.assertTrue;

import DomeinModellen.Klant;
import DomeinModellen.Recept;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DomeinTest{
    ArrayList<Recept> likes;
    @BeforeEach
public void initialize(){
       likes=new ArrayList<>();
        likes.add(new Recept("batat",15,"snacks"));

    }


    /**
     * Rigorous Test :-)
     */
    @Test
    public void gebruikersNaamMinderDan20Karakters()
    {
        Klant klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes);
        assertTrue("Het gebruikersnaam is te lang", klant.getGebruikersNaam().length()<20);
    }
    @Test
    public void wachtwoordMinderDan20Karakters()
    {
        Klant klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes);
        assertTrue("Het wachtwoord is te lang", klant.getWachtwoord().length()<20);
    }
}
