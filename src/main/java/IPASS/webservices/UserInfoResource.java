package IPASS.webservices;


import IPASS.DomeinModellen.Account;
import IPASS.DomeinModellen.Klant;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;
import java.util.ArrayList;

@Path("profile")
public class UserInfoResource {

    @GET
    @RolesAllowed("klant")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProfile(@Context SecurityContext sc){
        System.out.println("getproile");
        if (sc.getUserPrincipal() instanceof Account){
            Account current=(Account) sc.getUserPrincipal();
            return Response.ok(current).build();
        }else return Response.status(401).entity(new AbstractMap.SimpleEntry<String,String>("error","no user found")).build();

    }




    @POST
    @Path("newaccount")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newProfile(@FormParam("username") String userName,@FormParam("name") String name,@FormParam("passwoord")String passwoord,@FormParam("gender")String gender,@FormParam("email")String email){
        try {
            System.out.println("in new account resource");
            Account.addAccount(new Klant(userName,passwoord,gender,email,name,new ArrayList<>(),"klant",new ArrayList<>()));
            return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Succesfully signed up!\n you can now log in!")).build();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return Response.status(406).entity(new AbstractMap.SimpleEntry<String ,String >("result","Username or/and email adres already exists")).build();
        }


    }

    @POST
    @Path("wachtwoordveranderen")
    @Produces(MediaType.APPLICATION_JSON)
    public Response wachtwoordVeranderen(@FormParam("username") String userName,@FormParam("passwoord")String passwoord,@FormParam("email")String email){
        boolean accountFound=false;
        for (Account account:Account.getAlleAccounten()) {
            if (account.getEmailAdres().equals(email)&&account.getName().equals(userName)){
                accountFound=true;
                account.setWachtwoord(passwoord);
            }


        }
        if (accountFound){
            return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Wachtwoord Succesvol Veranderd")).build();
        }else return Response.status(401).entity(new AbstractMap.SimpleEntry<String,String>("error","no acc found")).build();




    }

}
