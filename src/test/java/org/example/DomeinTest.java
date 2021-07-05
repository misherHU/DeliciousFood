package org.example;


import static org.junit.jupiter.api.Assertions.*;

import IPASS.DomeinModellen.Klant;
import IPASS.DomeinModellen.Recept;
import IPASS.DomeinModellen.Review;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DomeinTest{
    private ArrayList<Recept> likes;
    private Klant klant;
    private Recept recept;

    @BeforeEach
    public void initialize() throws CloneNotSupportedException {
        likes=new ArrayList<>();
        //recept=new Recept("batat","15 minuten","snacks","fgbgf");

        likes.add(recept);
        klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes,"klant",new ArrayList<>());


    }



    @Test
    public void gebruikersNaamMinderDan20Karakters() throws CloneNotSupportedException {
        Klant klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes,"klant",new ArrayList<>());
        assertTrue(klant.getName().length()<20, "Het gebruikersnaam is te lang");
    }
    @Test
    public void wachtwoordMinderDan20Karakters() throws CloneNotSupportedException {
        Klant klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes,"klant",new ArrayList<>());

        assertTrue(klant.getWachtwoord().length()<20, "Het wachtwoord is te lang");
    }
    @Test
    public void reviewTekstMinderDan200Karakters()
    {

        Review review=new Review(klant.getName(),"grbhskugkersbuybkgresuybgkresuybgkrseyugrbeksyugrbsekyugbresyku");
        assertTrue(review.getTekst().length()<200, "Het review tekst is te lang");
    }


}
