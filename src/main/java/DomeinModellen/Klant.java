package DomeinModellen;

import java.util.ArrayList;

public class Klant extends Account{
    private ArrayList<Recept> likedRecepten;

    public Klant(String gebruikersNaam,String wachtwoord,String geslacht,String emailAdres,String naam,ArrayList<Recept> likedRecepten){
        super(gebruikersNaam,wachtwoord,geslacht,emailAdres,naam);
        this.likedRecepten=likedRecepten;

    }

    public ArrayList<Recept> getLikedRecepten() {
        return likedRecepten;
    }
    public void likeRecept(Recept recept){
        likedRecepten.add(recept);

    }


}
