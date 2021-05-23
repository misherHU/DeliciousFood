package org.example;

import static org.junit.Assert.assertTrue;

import DomeinModellen.Klant;
import DomeinModellen.Recept;
import DomeinModellen.Review;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DomeinTest{
    private ArrayList<Recept> likes;
    private Klant klant;
    private Recept recept;

    @BeforeEach
    public void initialize(){
        likes=new ArrayList<>();
        recept=new Recept("batat",15,"snacks");

        likes.add(recept);
        klant= new Klant("misher","meterman","man","mishernon@hotmail.com","wiwi",likes);


    }



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
    @Test
    public void reviewTekstMinderDan200Karakters()
    {

        Review review=new Review(klant,recept,"grbhskugkersbuybkgresuybgkresuybgkrseyugrbeksyugrbsekyugbresyku");
        assertTrue("Het review tekst is te lang", review.getTekst().length()<200);
    }


}
