package IPASS.webservices;

import IPASS.security.SecurityManager;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.AbstractMap.SimpleEntry;
import java.util.Calendar;

@Path("authentication")
public class AuthenticationResource {
    public static final Key key = MacProvider.generateKey();
    public static String huidigeUser;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("username") String username,
                                     @FormParam("passwoord") String passwoord) {
        try {
            System.out.println("begin auth");
            String role = SecurityManager.getInstance().validateLogin(username, passwoord);
            System.out.println("validated");
            if (role == null) throw new IllegalArgumentException("No user found or invalid credentials");
            String token = createToken(username, role);
            huidigeUser=username;
            System.out.println("na create token");
            SimpleEntry<String, String> JWTText = new SimpleEntry<>("JWT", token);
            return Response.ok(JWTText).build();
        } catch (JwtException | IllegalArgumentException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private String createToken(String username, String role) {
        Calendar expiration = Calendar.getInstance();
        expiration.add(Calendar.MINUTE, 30);

        return Jwts.builder()
                .setSubject(username)
                .setExpiration(expiration.getTime())
                .claim("role", role)
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }





}
