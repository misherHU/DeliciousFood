package IPASS.webservices;


import IPASS.DomeinModellen.Recept;
import IPASS.persistence.PersistenceManager;
import IPASS.security.SecurityManager;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.AbstractMap;

@Path("{receptNaam}")
public class ReceptInfoPaginaResource {
    @PathParam("receptNaam")
    private String receptNaam;


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("klant")
    public Response receptLiken(){


        return Response.ok().build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReceptInfo() throws IOException, ClassNotFoundException {
        System.out.println("in resource");
        if (Recept.getReceptByName(receptNaam)==null){
            System.out.println("receptname is null");
            return Response.status(404).entity(new AbstractMap.SimpleEntry<String,String>("error","Geen recepts met die naam")).build();
        }else return Response.ok(Recept.getReceptByName(receptNaam)).build();



    }


    @POST

    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getReceptInfoDoorForm(@FormParam("zoekOpdracht")String zoekOpdracht) throws IOException, ClassNotFoundException {
        System.out.println("in resource");
        if (Recept.getReceptByName(zoekOpdracht)==null){
            System.out.println("receptname is null");
            return Response.status(404).entity(new AbstractMap.SimpleEntry<String,String>("error","Geen recepts met die naam")).build();
        }else return Response.ok(Recept.getReceptByName(zoekOpdracht)).build();



    }



}
