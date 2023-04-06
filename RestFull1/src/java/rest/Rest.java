/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import worker.Wrk;

/**
 * REST Web Service
 *
 * @author desousar
 */
@Path("manga")
public class Rest {

    @Context
    private UriInfo context;
    
    private Wrk wrk;

    /**
     * Creates a new instance of Rest
     */
    public Rest() {
        wrk = new Wrk();
    }

    /**
     * Retrieves representation of an instance of rest.Rest
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of Rest
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }

    @GET
    @Path("getAuthor")
    @Produces(javax.ws.rs.core.MediaType.TEXT_PLAIN)
    public String getAuthor() {
        String test = "";
        System.out.println("test" + test);
        return " de Sousa Raphael";
    }
    
    
    @GET
    @Path("getManga")
    @Produces(MediaType.TEXT_PLAIN) 
    public String getManga() {
        //Gson builder = new Gson();
        //Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        //String toJson = builder.toJson(wrk.lireManga());
        //On affiche notre résultat.
        //HashMap<String, String> map = new HashMap<>();
        //map.put("type","test");
        return "test";
    }
}
