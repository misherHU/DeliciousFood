package IPASS.webservices;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import IPASS.DomeinModellen.Recept;

import java.io.IOException;
import java.util.ArrayList;

@Path("home")
public class HomePageResources {


    @GET
    @Produces("application/json")

    public Response getReceptenVanAzure() throws IOException, ClassNotFoundException {



        return Response.ok(Recept.alleRecepten).build();
    }




}








//World loadedWorld = (World)ois.readObject();
//                //World.setWorld(loadedWorld);