/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import com.google.gson.Gson;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import worker.Wrk;

/**
 * REST Web Service
 *
 * @author desousar
 */
@Path("user")
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
        return " Malcolm Gfeller";
    }

    @GET
    @Path("getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser(@QueryParam("PK") int pk) {
        Gson gson = new Gson();
        String json;
        try {
            json = gson.toJson(wrk.getUser(pk));
        } catch (Exception e) {
            json = gson.toJson("error");
        }
        return json;
    }

    @GET
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public String login(@QueryParam("username") String username, @QueryParam("password") String password) {
        Gson gson = new Gson();
        HashMap<String, String> result = new HashMap<>();
        try {
            boolean isValid = wrk.checkLogin(username, password);
            if (isValid) {
                result.put("status", "success");
            } else {
                result.put("status", "fail");
            }
        } catch (Exception e) {
            result.put("status", "error");
            result.put("message", e.getMessage());
        }
        return gson.toJson(result);
    }

}
