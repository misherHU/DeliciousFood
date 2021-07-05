package IPASS.DomeinModellen;

import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Objects;

public class Account implements Principal, Serializable {
    private String gebruikersNaam;
    private String wachtwoord;
    private String geslacht;
    private String emailAdres;
    private String naam;
    private String role;
    private static ArrayList<Account> alleAccounten=new ArrayList<>();


    public Account(String gebruikersNaam,String wachtwoord,String geslacht,String emailAdres,String naam,String role){
        this.gebruikersNaam=gebruikersNaam;
        this.wachtwoord=wachtwoord;
        this.geslacht=geslacht;
        this.emailAdres=emailAdres;
        this.naam=naam;
        this.role=role;



    }

    public String getRole() {
        return role;
    }

    public static ArrayList<Account> getAlleAccounten() {
        return alleAccounten;
    }

    public static Boolean findDuplicate(Account newAccount){
        boolean returnValue=false;
        for (Account existingAccount:alleAccounten) {
             if (existingAccount.gebruikersNaam.equals(newAccount.gebruikersNaam)||existingAccount.emailAdres.equals(newAccount.emailAdres)){
                 returnValue=true;
             }

        }
        return returnValue;

    }

    public static void setAlleAccounten(ArrayList<Account> alleAccounten) {
        Account.alleAccounten = alleAccounten;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getNaam() {
        return naam;
    }

    public String getEmailAdres() {
        return emailAdres;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    @Override
    public String getName() {
        return gebruikersNaam;
    }


    public static void addAccount(Account account){
        alleAccounten.add(account);
    }
    public boolean checkPassword(String password) {
        return this.wachtwoord.equals(password);
    }

}
