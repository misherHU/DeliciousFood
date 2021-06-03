package DomeinModellen;

import java.util.ArrayList;

public class Recept {
    private String naam;
    private String duratie;
    private int aantalLikes;
    private int aantalDisLikes;

    private String categorie;
    private ArrayList<String> ingridienten;
    private ArrayList<Review> reviews;

    public String getNaam() {
        return naam;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDuratie() {
        return duratie;
    }

    public Recept(String naam,String duratie,String categorie){
        this.naam=naam;
        this.duratie=duratie;
        this.categorie=categorie;
    }

    public int getAantalLikes() {
        return aantalLikes;
    }

}
