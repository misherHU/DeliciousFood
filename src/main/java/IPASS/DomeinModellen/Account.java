package IPASS.DomeinModellen;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Objects;

public class Account implements Principal {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(gebruikersNaam, account.gebruikersNaam) && Objects.equals(emailAdres, account.emailAdres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gebruikersNaam, emailAdres);
    }



    public static String validateLoginn(String username,String passwoord){
        System.out.println("in validateloginn");
        String returnValue=null;
        System.out.println(username+" "+passwoord);
        for (Account account:alleAccounten) {
            System.out.println(account.getName()+" "+account.wachtwoord);

            if (account.getName().equals(username)&&account.wachtwoord.equals(passwoord)){
                System.out.println("in if");
                returnValue= account.role;
            }
        }



        return returnValue;

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
