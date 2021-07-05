package IPASS.webservices;


import IPASS.DomeinModellen.Account;
import IPASS.DomeinModellen.Klant;
import IPASS.DomeinModellen.Recept;
import IPASS.persistence.PersistenceManager;
import IPASS.security.SecurityManager;

import javax.annotation.security.RolesAllowed;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;

@Path("{receptNaam}")
public class ReceptInfoPaginaResource {
    @PathParam("receptNaam")
    private String receptNaam;


    @POST
    @Path("like")
    @RolesAllowed("klant")
    @Produces(MediaType.APPLICATION_JSON)
    public Response receptLiken(@Context SecurityContext sc) throws CloneNotSupportedException {
        System.out.println("bep like");
        System.out.println("recept naam is "+receptNaam);
        boolean receptDisliked=false;
        Recept toBeRemoved=null;
        try {
            if (sc.getUserPrincipal() instanceof Klant) {

                Klant current = (Klant) sc.getUserPrincipal();

                for (Recept value : current.getDislikedRecepten()) {
                    System.out.println(value.getNaam());
                    System.out.println(Recept.getReceptByName(receptNaam).getNaam());
                    if (value.equals(Recept.getReceptByName(receptNaam))) {
                        receptDisliked=true;
                        toBeRemoved=value;
                        //current.getDislikedRecepten().remove(Recept.getReceptByName(receptNaam));
                        value.decrementDislikes();
                    }

                }

                if (receptDisliked){
                    current.getDislikedRecepten().remove(toBeRemoved);
                }
                current.likeRecept(Recept.getReceptByName(receptNaam));
                Recept.getReceptByName(receptNaam).incrementLikes();




                return Response.ok(new AbstractMap.SimpleEntry<String, String>("result", "Recept Liked!")).build();
            } else
                return Response.status(401).entity(new AbstractMap.SimpleEntry<String, String>("result", "no user found")).build();


        }catch (CloneNotSupportedException exc) {
            System.out.println("in clone catch");
            return Response.ok(new AbstractMap.SimpleEntry<String, String>("result", "Recept al Liked!")).build();


        }



    }



    @POST
    @Path("dislike")
    @RolesAllowed("klant")
    @Produces(MediaType.APPLICATION_JSON)
    public Response receptDislLiken(@Context SecurityContext sc){
        try{
            Recept toBeRemoved=null;
            boolean receptLiked=false;
            System.out.println("in try dislike");

            if (sc.getUserPrincipal() instanceof Klant){

                Klant current=(Klant) sc.getUserPrincipal();

                System.out.println("in resource getnaam "+Recept.getReceptByName(receptNaam).getNaam());
                for (Recept value:current.getLikedRecepten()) {
                    System.out.println("value "+ value.getNaam());
                    if (value.equals(Recept.getReceptByName(receptNaam))){
                        System.out.println("true");
                        receptLiked=true;
                        toBeRemoved=value;
                        //current.getLikedRecepten().remove(Recept.getReceptByName(receptNaam));
                        value.decrementLikes();


                    }


                }

                System.out.println("about to be romeved "+Recept.getReceptByName(receptNaam).getNaam());

                if (receptLiked){
                    current.getLikedRecepten().remove(toBeRemoved);
                    System.out.println("removed "+Recept.getReceptByName(receptNaam));
                }


                System.out.println("about todislike "+Recept.getReceptByName(receptNaam));
                current.dislikeRecept(Recept.getReceptByName(receptNaam));
                System.out.println("about to increment "+Recept.getReceptByName(receptNaam));
                Recept.getReceptByName(receptNaam).incrementDislikes();

                return Response.ok(new AbstractMap.SimpleEntry<String ,String>("result","Recept Disliked!")).build();
            }else return Response.status(401).entity(new AbstractMap.SimpleEntry<String,String>("result","no user found")).build();


        }catch (CloneNotSupportedException exc){
            System.out.println("inn catch");

            return Response.ok(new AbstractMap.SimpleEntry<String ,String>("result","Recept al disliket")).build();

        }


    }





    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReceptInfo() throws IOException, ClassNotFoundException {
        System.out.println("in resource");
        System.out.println(receptNaam);
        if (Recept.getReceptByName(receptNaam)==null){
            System.out.println("receptname is null");
            return Response.status(404).entity(new AbstractMap.SimpleEntry<String,String>("error","Geen recepts met die naam")).build();
        }else {
            System.out.println("in else");
            return Response.ok(Recept.getReceptByName(receptNaam)).build();

        }



    }


    @POST
    @Path("zoekresultaat")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getReceptInfoDoorForm() throws IOException, ClassNotFoundException {
        System.out.println("in resource");
        if (Recept.getReceptByName(receptNaam)==null){
            System.out.println("receptname is null");
            return Response.status(404).entity(new AbstractMap.SimpleEntry<String,String>("error","Geen recepts met die naam")).build();
        }else return Response.ok(Recept.getReceptByName(receptNaam)).build();



    }


    @POST
    @Path("review")
    @RolesAllowed("klant")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response plaatsReview(@Context SecurityContext sc,@FormParam("opinie") String tekst){
        if (sc.getUserPrincipal() instanceof Klant){
            System.out.println(tekst);
            Klant current=(Klant) sc.getUserPrincipal();
            current.reviewRecept(Recept.getReceptByName(receptNaam),tekst);
            System.out.println("ging goed");

            return Response.ok(new AbstractMap.SimpleEntry<String ,String>("result","Succes")).build();
        }

       return Response.ok(new AbstractMap.SimpleEntry<String,String>("result","failed")).build();
    }



}
