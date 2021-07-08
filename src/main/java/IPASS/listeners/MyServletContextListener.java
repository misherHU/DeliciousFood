package IPASS.listeners;

import IPASS.DomeinModellen.*;
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
import java.util.ArrayList;


@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
//            ArrayList<String> inglist=new ArrayList<>();
//            inglist.add("mais");
//            inglist.add("rijst");
//            inglist.add("wortel");
//
//
//            ArrayList<String> stplist=new ArrayList<>();
//           stplist.add("gooien");
//            stplist.add("branden");
//            stplist.add("eten");
//
//            ArrayList<String> roastedchickIngr=new ArrayList<>();
//            roastedchickIngr.add("4-5 Potatoes, quartered  ");
//            roastedchickIngr.add("2 lb. (900g) chicken thighs and legs, bone in and skin on");
//            roastedchickIngr.add("1 large onion, cut into large chunks");
//            roastedchickIngr.add("5-6 tablespoons olive oil, divided");
//            roastedchickIngr.add("5-6 garlic cloves, unpeeled ");
//            roastedchickIngr.add("1 teaspoon dried oregano ");
//            roastedchickIngr.add("fresh Rosemary ");
//            roastedchickIngr.add("1 teaspoon dried thyme ");
//            roastedchickIngr.add("1½ teaspoons paprika ");
//            roastedchickIngr.add("1 teaspoon garlic powder");
//            roastedchickIngr.add("1 teaspoon Salt OR to taste ");
//            roastedchickIngr.add("1 teaspoon Black pepper");
//            roastedchickIngr.add("1 tablespoon lemon juice ");
//            roastedchickIngr.add("1/2 teaspoon Cumin ");
//
//            ArrayList<String> roastedchickstp=new ArrayList<>();
//            roastedchickstp.add(" In a large bowl mix olive oil and all the spices. Place chicken in the bowl and mix until well coated.  Cover the bowl and marinate for at least 1 hour. ");
//            roastedchickstp.add(" Preheat oven to 425°F (220°C). Prepare a large baking pan or baking sheet. Set aside.");
//            roastedchickstp.add("Cut potatoes into quarters. Place in a large bowl, add salt, rosemary and olive oil, toss all together.");
//            roastedchickstp.add(" Spread the potatoes in the pan, add marinated chicken, whole garlic cloves (with the peel), onion chunks and few more rosemary branches. ");
//            roastedchickstp.add("Roast for about 1 hour, until chicken is fully cooked and potatoes are golden brown. ");
//
//
//
//
//
//
//
//
//            Account b1=new Beheerder("jo","jo","man","jo@mail.com","mishernon1","beheerder");
//
//
//
//            System.out.println("in de context listener");
//            //PersistenceManager.saveWorldToAzure();
//
//            ArrayList<Review> revlist=new ArrayList<>();
//
//            Recept p=new Recept("Korean Fried Chicken","15 minuten","Chicken","https://www.youtube.com/embed/aFLFujQNX9s","KoreanFriedChicken15.PNG",inglist,0,0,stplist,new ArrayList<>());
//            Recept l=new Recept("One Pan Chicken and Broccoli Stir Fry","30 minuten","Chicken","https://www.youtube.com/embed/A533gxxl6RQ","OnePanChickenandBroccoliStirFry.PNG",inglist,0,0,stplist,new ArrayList<>());
//            Recept j=new Recept("Roasted Chicken & Potatoes","1 uur","Chicken","https://www.youtube.com/embed/2bYkOhDnBYM","RoastedChicken&Potatoes.PNG",roastedchickIngr,0,0,roastedchickstp,new ArrayList<>());
//            Recept g=new Recept("Triple Smash with Bacon & Cheese","15 minuten","Burger","https://www.youtube.com/embed/98AAe_EMOaw","TripleSmashwithBacon&Cheese.PNG",inglist,0,0,stplist,new ArrayList<>());
//            Recept d=new Recept("Mexi-Mac Burger","30 minuten","Burger","https://www.youtube.com/embed/StYm5C6PafU","MexiMac30.PNG",inglist,0,0,stplist,new ArrayList<>());
//            Recept u=new Recept("Shrim Scampi Pasta","15 minuten","Pasta","https://www.youtube.com/embed/gsLwEutE6AU","ShrimpScampiPasta15.PNG",inglist,0,0,stplist,new ArrayList<>());
//            Recept i=new Recept("One-Pan Spanish Garlic Pasta","30 minuten","Pasta","https://www.youtube.com/embed/nZDBocBhXNk","One-PanSpanishGarlicPasta.PNG",inglist,0,0,stplist,new ArrayList<>());
//
//
//            Account a1=new Klant("mishw","mik","man","mish@mail.com","mishernon",new ArrayList<>(),"klant",new ArrayList<>());
//            revlist.add(new Review(a1.getName(),"hahahahaha craaaazyyyyyyyyyyyy craaazyyyy"));
//            g.addReview(a1.getName(),"hahahahaha craaaazyyyyyyyyyyyy craaazyyyy wahhhhhhh");

            // korean fried chicken, tripple smash with bacon and cheese
            // <iframe width="560" height="315" src="https://www.youtube.com/embed/aFLFujQNX9s" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
            //<iframe width="560" height="315" src="https://www.youtube.com/embed/98AAe_EMOaw" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>



            //PersistenceManager.loadWorldFromAzure();

            //SecurityManager.getInstance().registerUser(new MyUser("admin", "admin", "admin"));

            PersistenceManager.loadAccountenFromAzure();
            PersistenceManager.loadReceptenFromAzure();
            System.out.println("staarting.....");
        } catch (Exception e) {
            System.out.println("Error loading world: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("begin context destroyer");
        try {
            PersistenceManager.saveAccountsToAzure();
            System.out.println(1);
            PersistenceManager.saveReceptenToAzure();
            System.out.println(2);
        } catch (IOException | CloneNotSupportedException e) {
            e.printStackTrace();
        }
//        try {

//            System.out.println("in de try");

//        try {
//            PersistenceManager.saveDataToAzure();
//        } catch (IOException | CloneNotSupportedException e) {
//            e.printStackTrace();
//        }

        System.out.println("sluiten");

//        } catch (Exception e) {
//            System.out.println("Error saving world: " + e.getMessage());
//        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
