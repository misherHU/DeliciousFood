package DomeinModellen;

import java.util.ArrayList;

public class Account {
    private String gebruikersNaam;
    private String wachtwoord;
    private String geslacht;
    private String emailAdres;
    private String naam;


    public Account(String gebruikersNaam,String wachtwoord,String geslacht,String emailAdres,String naam){
        this.gebruikersNaam=gebruikersNaam;
        this.wachtwoord=wachtwoord;
        this.geslacht=geslacht;
        this.emailAdres=emailAdres;
        this.naam=naam;

    }

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
