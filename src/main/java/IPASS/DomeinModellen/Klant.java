package IPASS.DomeinModellen;

import java.util.ArrayList;

public class Klant extends Account{
    private ArrayList<Recept> likedRecepten;

    public Klant(String gebruikersNaam,String wachtwoord,String geslacht,String emailAdres,String naam,ArrayList<Recept> likedRecepten,String role) throws CloneNotSupportedException {
        super(gebruikersNaam,wachtwoord,geslacht,emailAdres,naam,role);
        this.likedRecepten=likedRecepten;
        for (Account account:Account.getAlleAccounten()) {
            if (this.equals(account)){
                throw new CloneNotSupportedException("Dit account bestaat al.");
            }


        }
        Account.addAccount(this);

    }

    public ArrayList<Recept> getLikedRecepten() {
        return likedRecepten;
    }
    public void likeRecept(Recept recept){
        likedRecepten.add(recept);

    }


}
