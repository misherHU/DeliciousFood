package IPASS.webservices;


import IPASS.DomeinModellen.Account;
import IPASS.DomeinModellen.Beheerder;
import IPASS.DomeinModellen.Recept;
import IPASS.DomeinModellen.Review;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;

@Path("admin")
public class BeheerderResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response beheerderInfo(@Context SecurityContext sc){
        if (sc.getUserPrincipal() instanceof Account){
            Beheerder current=(Beheerder) sc.getUserPrincipal();
            if (!current.getRole().equals("beheerder")){
                return Response.status(403).entity(new AbstractMap.SimpleEntry<String,String>("error","Restricted! Content is Alleen Beschikbaar Voor Beheerder")).build();
            }else return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","")).build();


        }else {
            System.out.println("in catch");
        }return Response.status(401).entity(new AbstractMap.SimpleEntry<String,String>("error","Restricted! Content is Alleen Beschikbaar Voor Beheerder")).build();

    }


    @POST
    @Path("deleteklant")
    @RolesAllowed("beheerder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteKlant(@Context SecurityContext sc, @FormParam("klantGebruikersNaam")String gebruikersNaam){
        System.out.println("in delete klant");
        if (sc.getUserPrincipal() instanceof Account){
            Beheerder current=(Beheerder) sc.getUserPrincipal();
            if (current.deleteKlant(gebruikersNaam)){
                return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Klant verwijderd!")).build();
            }else return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Klant niet gevonden!")).build();


        }else return Response.status(401).entity(new AbstractMap.SimpleEntry<String,String>("error","no user found")).build();


    }



    @POST
    @Path("deleterecept")
    @RolesAllowed("beheerder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRecept(@Context SecurityContext sc, @FormParam("receptNaam")String receptNaam){
        System.out.println("in delete recept");
        if (sc.getUserPrincipal() instanceof Account){
            Beheerder current=(Beheerder) sc.getUserPrincipal();
            System.out.println("receptnaam "+receptNaam);
            if (current.deleteRecept(receptNaam)){
                System.out.println(Arrays.toString(Recept.alleRecepten.toArray()));
                return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Recept verwijderd!")).build();
            }else return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Recept niet gevonden!")).build();


        }else return Response.status(401).entity(new AbstractMap.SimpleEntry<String,String>("error","no user found")).build();


    }




    @POST
    @Path("addrecept")
    @RolesAllowed("beheerder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addRecept(@Context SecurityContext sc, @FormParam("receptNaam")String receptNaam,@FormParam("categorie")String categorie,@FormParam("duratie") String duratie,@FormParam("youtubeVideoSrc") String youtubeVideoSrc,@FormParam("imgSrc") String imgSrc,@FormParam("ingredienten") String ingredienten,@FormParam("stappen") String stappen){
        System.out.println("in delete recept");


        ArrayList<String> ingredientenList=new ArrayList<>();
        for (int i=0;i<ingredienten.split(",").length;i++){
            ingredientenList.add(ingredienten.split(",")[i]);

        }


        ArrayList<String> stappenList=new ArrayList<>();
        for (int i=0;i<stappen.split(",").length;i++){
            stappenList.add(stappen.split(",")[i]);

        }


        if (sc.getUserPrincipal() instanceof Account){
            Beheerder current=(Beheerder) sc.getUserPrincipal();
//                public Recept(String naam, String duratie, String categorie,String youtubeVideoSrc,String imgSrc,ArrayList<String> ingredienten,int aantalDisLikes,int aantalLikes,ArrayList<String> stappen,ArrayList< Review > reviews) throws CloneNotSupportedException {

                try{
                Recept newRecept=new Recept(receptNaam,duratie,categorie,youtubeVideoSrc,imgSrc,ingredientenList,0,0,stappenList,new ArrayList<>());
                    System.out.println(Arrays.toString(Recept.alleRecepten.toArray()));
                return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Recept toegevoegd!")).build();
            }catch (CloneNotSupportedException exc){
                    return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Recept Naam Bestaat al!")).build();

                }




        }else return Response.status(401).entity(new AbstractMap.SimpleEntry<String,String>("error","no user found")).build();


    }


    @POST
    @Path("changerecept/{receptnaam}/{eigenschaap}/{value}")
    @RolesAllowed("beheerder")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeRecept(@PathParam("receptnaam")String receptNaam,@PathParam("eigenschaap")String eigenschaap,@PathParam("value")String value){
        System.out.println("in vernaderen");
        if (Recept.getReceptByName(receptNaam)==null){
            return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Recept naam bestaat niet")).build();
        }else {
            switch (eigenschaap){
                case "naam":
                    Recept.getReceptByName(receptNaam).setNaam(value);
                    break;

                case "duratie":
                    Recept.getReceptByName(receptNaam).setDuratie(value);

                case "categorie":
                    Recept.getReceptByName(receptNaam).setCategorie(value);

                case "youtubeVideoSrc":
                    Recept.getReceptByName(receptNaam).setYoutubeVideoSrc(value);

                case "imgSrc":
                    Recept.getReceptByName(receptNaam).setImgSrc(value);

                case "ingredienten":
                    ArrayList<String> ingredientenList=new ArrayList<>();
                    for (int i=0;i<value.split(",").length;i++){
                        ingredientenList.add(value.split(",")[i]);

                    }

                    Recept.getReceptByName(receptNaam).setIngredienten(ingredientenList);

            }

        }
        return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","Recept succesvol veranderd")).build();


    }







}
