package org.example;

import static org.junit.jupiter.api.Assertions.*;

import IPASS.DomeinModellen.Klant;
import IPASS.DomeinModellen.Recept;
import IPASS.DomeinModellen.Review;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class DomeinTest{
    private static ArrayList<Recept> likes;
    private static Klant klant;
    private static Recept recept;

    @BeforeAll
    static void initialize() throws CloneNotSupportedException {
        likes=new ArrayList<>();
        //recept=new Recept("batat","15 minuten","snacks","fgbgf");

        likes.add(recept);
        klant= new Klant("misher1","meterman","man","mishernon1@hotmail.com","wiwi",likes,"klant",new ArrayList<>());


    }



    @Test
    public void gebruikersNaamMinderDan20Karakters() throws CloneNotSupportedException {
        Klant klant= new Klant("misher2","meterman","man","mishernon2@hotmail.com","wiwi",likes,"klant",new ArrayList<>());
        assertTrue(klant.getName().length()<20, "Het gebruikersnaam is te lang");
    }
    @Test
    public void wachtwoordMinderDan20Karakters() throws CloneNotSupportedException {
        Klant klant= new Klant("misher3","meterman","man","mishernon3@hotmail.com","wiwi",likes,"klant",new ArrayList<>());

        assertTrue(klant.getWachtwoord().length()<20, "Het wachtwoord is te lang");
    }
    @Test
    public void reviewTekstMinderDan200Karakters()
    {
        System.out.println(klant.getName());

        Review review=new Review(klant.getName(),"grbhskugkersbuybkgresuybgkresuybgkrseyugrbeksyugrbsekyugbresyku");
        assertTrue(review.getTekst().length()<200, "Het review tekst is te lang");
    }


}
