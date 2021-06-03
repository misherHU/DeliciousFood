package listeners;

import DomeinModellen.Recept;
import persistence.PersistenceManager;
import security.MyUser;
import security.SecurityManager;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.Duration;
import java.util.ArrayList;

import static java.lang.System.*;

@WebListener
public class MyServletContextListener implements ServletContextListener {
    public static ArrayList<Recept> alles;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            alles=new ArrayList<>();
            alles.add(new Recept("Korean Fried Chicken","15 minuten","Chicken"));
            alles.add(new Recept("One Pan Chicken and Broccoli Stir Fry","30 minuten","Chicken"));
            alles.add(new Recept("Roasted Chicken & Potatoes","1 uur","Chicken"));
            alles.add(new Recept("Triple Smash w/Bacon & Cheese","15 minuten","Burger"));
            alles.add(new Recept("Mexi-Mac Burger","30 minuten","Burger"));
            alles.add(new Recept("Shrim Scampi Pasta","15 minuten","Pasta"));
            alles.add(new Recept("One-Pan Spanish Garlic Pasta","30 minuten","Pasta"));
            //PersistenceManager.loadWorldFromAzure();

            SecurityManager.getInstance().registerUser(new MyUser("admin", "admin", "admin"));
            out.println("prome ke saved");
            //PersistenceManager.saveWorldToAzure();
            out.println("despues");
        } catch (Exception e) {
            out.println("Error loading world: " + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        out.println("kbi drentekoi");
        try {
            out.println("den cntxt destr");
            //PersistenceManager.saveWorldToAzure();


            out.println("dspey saved");

        } catch (Exception e) {
            out.println("Error saving world: " + e.getMessage());
        }

        Schedulers.shutdownNow();
        HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();
    }
}
