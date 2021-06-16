package IPASS.DomeinModellen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review {
    private Klant account;
    private Recept recept;
    private String tekst;
    private String datum;


    public Review(Klant account,Recept recept,String tekst){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime nu = LocalDateTime.now();
        //System.out.println(dateTimeFormatter.format(nu));
        this.account=account;
        this.recept=recept;
        this.tekst=tekst;
        this.datum=dateTimeFormatter.format(nu);
    }

    public String getTekst() {
        return tekst;
    }

    public String getDatum() {
        return datum;
    }
}
