package IPASS.webservices;


import IPASS.DomeinModellen.Account;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.AbstractMap;

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
}
