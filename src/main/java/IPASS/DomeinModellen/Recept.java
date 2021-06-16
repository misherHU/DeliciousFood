package IPASS.DomeinModellen;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Recept implements Serializable {
    //public String geselcteerdeRecept;
    private String naam;
    private String duratie;
    private int aantalLikes;
    private int aantalDisLikes;
    public static ArrayList<Recept> alleRecepten=new ArrayList<>();

    private String youtubeVideoSrc;
    private String categorie;
    private ArrayList<String> ingridienten;
    private ArrayList<Review> reviews;
    private ArrayList<String> stappen;






    public Recept(String naam, String duratie, String categorie,String youtubeVideoSrc) throws CloneNotSupportedException {
        this.naam=naam;
        this.duratie=duratie;
        this.categorie=categorie;
        this.youtubeVideoSrc=youtubeVideoSrc;
        System.out.println("voor for lus");
        for (Recept recept: alleRecepten) {
            if (this.equals(recept)){
                throw new CloneNotSupportedException("Dit recept bestaat al");

            }


        }
        System.out.println("na for lus");
        alleRecepten.add(this);
    }

    public String getYoutubeVideoSrc() {
        return youtubeVideoSrc;
    }

    public String getNaam() {
        return naam;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getDuratie() {
        return duratie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recept recept = (Recept) o;
        return Objects.equals(naam, recept.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

//    public int getAantalLikes() {
//        return aantalLikes;
//    }

    public static Recept getReceptByName(String name){
        Recept returnValue=null;
        for (Recept recept:alleRecepten) {
             if (recept.naam.equals(name)){
                 returnValue= recept;
             }

        }
        return returnValue;

    }

}
