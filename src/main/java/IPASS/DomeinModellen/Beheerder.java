package IPASS.DomeinModellen;

import java.util.ArrayList;

public class Beheerder extends Account{

    public Beheerder(String gebruikersNaam, String wachtwoord, String geslacht, String emailAdres, String naam, String role) throws CloneNotSupportedException {
        super(gebruikersNaam, wachtwoord, geslacht, emailAdres, naam, role);

        if (Account.findDuplicate(this)){
            throw new CloneNotSupportedException("Dit account bestaat al.");
        }
        Account.addAccount(this);
    }


        public  boolean deleteKlant(String accountUserName){
            boolean returnValue=false;
            Klant toBeRemoved=null;
            System.out.println("username "+accountUserName);
            for (Account klant:Account.getAlleAccounten()) {
                if (klant.getName().equals(accountUserName)){
                    toBeRemoved= (Klant) klant;

                    System.out.println("true");

                    returnValue=true;
                }


            }
            if (!returnValue){
                System.out.println("niet gevonden");
                throw new NullPointerException("Geen klant met die naam bestaat");
            }else {
                System.out.println("deleting");
                Account.getAlleAccounten().remove(toBeRemoved);
            }


            return returnValue;
        }




        public boolean deleteRecept(String receptNaam){
        boolean returnValue=false;
        Recept toBeRemoved=null;
            for (Recept recept:Recept.alleRecepten) {
                 if (recept.getNaam().equals(receptNaam)){

                     toBeRemoved=recept;
                     returnValue=true;
                 }

            }

            if (toBeRemoved!=null){
                System.out.println("not null");
                Recept.alleRecepten.remove(toBeRemoved);
            }
            return returnValue;

        }








}
