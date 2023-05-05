/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
        Gson builder = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        String manga = builder.toJson(wrk.lireManga());
//        On affiche notre résultat.
        return manga;
    }

    @POST
    @Path("ajoutManga")
    @Produces(MediaType.TEXT_PLAIN)
    public String putManga(@FormParam("nomDuManga") String nomDuManga, @FormParam("nomDuTome") String nomDuTome, @FormParam("numDuTome") String numeroDuTome, @FormParam("image") String image) {
        Gson gson = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        boolean ok = wrk.addManga(nomDuTome, nomDuManga, numeroDuTome, image);
//        On affiche notre résultat.
        return gson.toJson(ok);
    }
    
    @PUT
    @Path("modifyManga")
    @Produces(MediaType.TEXT_PLAIN)
    public String modifyManga(@FormParam("Pk") int pk,@FormParam("nomDuManga") String nomDuManga, @FormParam("nomDuTome") String nomDuTome, @FormParam("numDuTome") String numeroDuTome, @FormParam("image") String image) {
        Gson gson = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        boolean ok = wrk.modifyManga(pk,nomDuTome, nomDuManga, numeroDuTome, image);
//        On affiche notre résultat.
        return gson.toJson(ok);
    }
    @DELETE
    @Path("deleteManga")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteManga(@QueryParam("Pk") int pk) {
        Gson gson = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        boolean ok = wrk.deleteManga(pk);
//        On affiche notre résultat.
        return gson.toJson(ok);
    }

    @GET
    @Path("getFavoris")
    @Produces(MediaType.TEXT_PLAIN)
    public String getMangaUser(@QueryParam("u") int user) {
        Gson builder = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        String mangaUser = builder.toJson(wrk.lireFavoris(user));
//        On affiche notre résultat.
        return mangaUser;
    }
    
    @POST
    @Path("ajoutFavoris")
    @Produces(MediaType.TEXT_PLAIN)
    public String addFavoris(@FormParam("fkUser") int fkUser,@FormParam("fkManga") int fkManga) {
        Gson gson = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        boolean ok = wrk.addFavoris(fkUser,fkManga);
//        On affiche notre résultat.
        return gson.toJson(ok);
    }
    
    @PUT
    @Path("modifyFavoris")
    @Produces(MediaType.TEXT_PLAIN)
    public String modifyFavoris(@FormParam("newFkUser") int newFkUser,@FormParam("newFkManga") int newFkManga, @FormParam("oldFkUser") int oldFkUser, @FormParam("oldFkManga") int oldFkManga) {
        Gson gson = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        boolean ok = wrk.modifyFavoris(newFkUser,newFkManga,oldFkUser,oldFkManga);
//        On affiche notre résultat.
        return gson.toJson(ok);
    }
    @DELETE
    @Path("deleteFavoris")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteFavoris(@FormParam("FKM") int fkManga,@FormParam("FKU") int fkUser) {
        Gson gson = new Gson();
//        Le constructeur va transformer notre ArrayList de résultats dans un format JSON.
        boolean ok = wrk.deleteFavoris(fkUser,fkManga);
//        On affiche notre résultat.
        return gson.toJson(ok);
    }
}
