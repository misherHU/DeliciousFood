package setup;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.annotation.security.RolesAllowed;

public class JerseyConfig extends ResourceConfig {
public JerseyConfig(){
    packages("Webservicess");
    register(RolesAllowedDynamicFeature.class);

}
}
