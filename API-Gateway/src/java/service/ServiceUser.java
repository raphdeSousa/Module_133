/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/JerseyClient.java to edit this template
 */
package service;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:Rest [user]<br>
 * USAGE:
 * <pre>
 *        ServiceUser client = new ServiceUser();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author GfellerM01
 */
public class ServiceUser {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://gfellerm01.emf-informatique.ch/javaServiceRest1/webresources";

    public ServiceUser() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("user");
    }

    public String getAuthor() throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("getAuthor");
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public String getUser(String PK) throws ClientErrorException {
        WebTarget resource = webTarget;
        if (PK != null) {
            resource = resource.queryParam("PK", PK);
        }
        resource = resource.path("getUser");
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
    }

    public String getXml() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public String login() throws ClientErrorException {
        return webTarget.path("login").request().post(null, String.class);
    }

    public void putXml(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }

    public void close() {
        client.close();
    }
    
}
