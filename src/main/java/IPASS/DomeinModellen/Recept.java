package IPASS.DomeinModellen;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Recept implements Serializable {

    private String naam;
    private String duratie;
    private int aantalLikes;
    private int aantalDisLikes;
    public static ArrayList<Recept> alleRecepten=new ArrayList<>();

    private String youtubeVideoSrc;
    private String categorie;
    private String imgSrc;
    private ArrayList<String> ingredienten;
    private ArrayList<Review> reviews;
    private ArrayList<String> stappen;






    public Recept(String naam, String duratie, String categorie,String youtubeVideoSrc,String imgSrc,ArrayList<String> ingredienten,int aantalDisLikes,int aantalLikes,ArrayList<String> stappen,ArrayList<Review> reviews) throws CloneNotSupportedException {
        this.naam=naam;
        this.duratie=duratie;
        this.categorie=categorie;
        this.youtubeVideoSrc=youtubeVideoSrc;
        this.imgSrc=imgSrc;
        this.ingredienten=ingredienten;
        this.aantalLikes=aantalLikes;
        this.aantalDisLikes=aantalDisLikes;
        this.stappen=stappen;
        this.reviews=reviews;

        System.out.println("voor for lus");
        for (Recept recept: alleRecepten) {
            if (this.equals(recept)){
                throw new CloneNotSupportedException("Dit recept bestaat al");

            }


        }
        System.out.println("na for lus");
        alleRecepten.add(this);
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setDuratie(String duratie) {
        this.duratie = duratie;
    }

    public void setYoutubeVideoSrc(String youtubeVideoSrc) {
        this.youtubeVideoSrc = youtubeVideoSrc;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public void setIngredienten(ArrayList<String> ingredienten) {
        this.ingredienten = ingredienten;
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

    public String getImgSrc() {
        return imgSrc;
    }

    public ArrayList<String> getIngredienten() {
        return ingredienten;
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

    public int getAantalLikes() {
        return aantalLikes;
    }

    public int getAantalDisLikes() {
        return aantalDisLikes;
    }

    public ArrayList<String> getStappen() {
        return stappen;
    }

    public static Recept getReceptByName(String name){
        Recept returnValue=null;
        for (Recept recept:alleRecepten) {
             if (recept.naam.equals(name)){
                 returnValue= recept;
             }

        }
        if (returnValue!=null){
            System.out.println("in getnaam functie "+returnValue.getNaam());
        }

        return returnValue;

    }




    public void addReview(String gebruikersNaam,String tekst){
        reviews.add(new Review(gebruikersNaam,tekst));
    }





    public  void incrementLikes(){
        this.aantalLikes=aantalLikes+1;

    }

    public  void incrementDislikes(){
        this.aantalDisLikes=this.aantalDisLikes+1;

    }

    public  void decrementLikes(){
        this.aantalLikes=this.aantalLikes-1;

    }

    public  void decrementDislikes(){
      this.aantalDisLikes=this.aantalDisLikes-1;

    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }
}
