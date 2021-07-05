package IPASS.DomeinModellen;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Review implements Serializable {
    private String gebruikersNaam;
    private String tekst;
    private String datum;
    public static Review alleReviews;




    public Review(String gebruikersNaam,String tekst){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime nu = LocalDateTime.now();
        this.gebruikersNaam=gebruikersNaam;

        this.tekst=tekst;
        this.datum=dateTimeFormatter.format(nu);
    }

    public String getTekst() {
        return tekst;
    }

    public String getDatum() {
        return datum;
    }

    public String getGebruikersNaam() {
        return gebruikersNaam;
    }
}
