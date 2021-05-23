package DomeinModellen;

import java.util.Date;

public class Review {
    private Klant account;
    private Recept recept;
    private String tekst;
    private Date datum;

    public Review(Klant account,Recept recept,String tekst,Date datum){
        this.account=account;
        this.recept=recept;
        this.tekst=tekst;
        this.datum=datum;
    }


}
