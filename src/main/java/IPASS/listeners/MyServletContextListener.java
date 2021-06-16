package IPASS.listeners;

import IPASS.DomeinModellen.Account;
import IPASS.DomeinModellen.Klant;
import IPASS.DomeinModellen.Recept;
import IPASS.persistence.PersistenceManager;
import IPASS.security.MyUser;
import IPASS.security.SecurityManager;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.Duration;



@WebListener
public class MyServletContextListener implements ServletContextListener {
    //public static ArrayList<Recept> alles;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            //<iframe width="560" height="315" src="https://www.youtube.com/embed/98AAe_EMOaw" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            Recept p=new Recept("Korean Fried Chicken","15 minuten","Chicken","https://www.youtube.com/embed/98AAe_EMOaw");
            Recept l=new Recept("One Pan Chicken and Broccoli Stir Fry","30 minuten","Chicken","https://www.youtube.com/embed/98AAe_EMOaw");
            Recept j=new Recept("Roasted Chicken & Potatoes","1 uur","Chicken","https://www.youtube.com/embed/98AAe_EMOaw");
            Recept g=new Recept("Triple Smash with Bacon & Cheese","15 minuten","Burger","https://www.youtube.com/embed/98AAe_EMOaw");
            Recept d=new Recept("Mexi-Mac Burger","30 minuten","Burger","https://www.youtube.com/embed/98AAe_EMOaw");
            Recept u=new Recept("Shrim Scampi Pasta","15 minuten","Pasta","https://www.youtube.com/embed/98AAe_EMOaw");
            Recept i=new Recept("One-Pan Spanish Garlic Pasta","30 minuten","Pasta","https://www.youtube.com/embed/98AAe_EMOaw");



            //PersistenceManager.loadWorldFromAzure();

            //SecurityManager.getInstance().registerUser(new MyUser("admin", "admin", "admin"));
            Account a1=new Klant("mishw","mik","man","mish@mail.com","mishernon",Recept.alleRecepten,"klant");
            System.out.println("in de context listener");
            //PersistenceManager.saveWorldToAzure();

        } catch (Exception e) {
            System.out.println("Error loading world: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("begin context destroyer");
//        try {

            System.out.println("in de try");

        try {
            PersistenceManager.saveDataToAzure();
        } catch (IOException | CloneNotSupportedException e) {
            e.printStackTrace();
        }

        System.out.println("na het saven");

//        } catch (Exception e) {
//            System.out.println("Error saving world: " + e.getMessage());
//        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
