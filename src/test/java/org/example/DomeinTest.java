package org.example;

import static org.junit.Assert.assertTrue;

import IPASS.DomeinModellen.Klant;
import IPASS.DomeinModellen.Recept;
import IPASS.DomeinModellen.Review;
import org.junit.Test;
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
        recept=new Recept("batat","15 minuten","snacks","fgbgf");

        likes.add(recept);
        klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes,"klant");


    }



    @Test
    public void gebruikersNaamMinderDan20Karakters() throws CloneNotSupportedException {
        Klant klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes,"klant");
        assertTrue("Het gebruikersnaam is te lang", klant.getName().length()<20);
    }
    @Test
    public void wachtwoordMinderDan20Karakters() throws CloneNotSupportedException {
        Klant klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes,"klant");

        assertTrue("Het wachtwoord is te lang", klant.getWachtwoord().length()<20);
    }
    @Test
    public void reviewTekstMinderDan200Karakters()
    {

        Review review=new Review(klant,recept,"grbhskugkersbuybkgresuybgkresuybgkrseyugrbeksyugrbsekyugbresyku");
        assertTrue("Het review tekst is te lang", review.getTekst().length()<200);
    }


}
