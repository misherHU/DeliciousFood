package IPASS.DomeinModellen;

import java.util.ArrayList;

public class Klant extends Account{
    private ArrayList<Recept> likedRecepten;
    private ArrayList<Recept> dislikedRecepten;

    public Klant(String gebruikersNaam,String wachtwoord,String geslacht,String emailAdres,String naam,ArrayList<Recept> likedRecepten,String role,ArrayList<Recept> dislikedRecepten) throws CloneNotSupportedException {
        super(gebruikersNaam,wachtwoord,geslacht,emailAdres,naam,role);
        this.likedRecepten=likedRecepten;
        this.dislikedRecepten=dislikedRecepten;
        if (Account.findDuplicate(this)){
            throw new CloneNotSupportedException("Dit account bestaat al.");
            }



        Account.addAccount(this);

    }

    public boolean findDuplicateLikedRecept(Recept recept){
       boolean returnValue=false;
        for (Recept value:likedRecepten) {
             if (value.equals(recept)){
                returnValue=true;
             }
            
        }
        
        return returnValue;
    }
    public boolean findDuplicateDisLikedRecept(Recept recept){
        boolean returnValue=false;
        for (Recept value:dislikedRecepten) {
            if (value.equals(recept)){
                returnValue=true;
            }

        }

        return returnValue;
    }


    public ArrayList<Recept> getLikedRecepten() {
        return likedRecepten;
    }


    public void likeRecept(Recept recept) throws CloneNotSupportedException {
        if (!this.findDuplicateLikedRecept(recept)){
            likedRecepten.add(recept);

        }else throw new CloneNotSupportedException("Recept al geliket");

    }



    public void reviewRecept(Recept recept,String tekst){
            recept.addReview(getName(),tekst);
    }






    public ArrayList<Recept> getDislikedRecepten() {
        return dislikedRecepten;
    }

    public void dislikeRecept(Recept recept) throws CloneNotSupportedException {
        if (!this.findDuplicateDisLikedRecept(recept)){

            dislikedRecepten.add(recept);

        }else throw new CloneNotSupportedException("Recept al disliket");
    }
}
