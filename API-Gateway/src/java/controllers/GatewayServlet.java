/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import beans.BeanInfo;
import beans.ParameterStringBuilder;
import beans.SimpleResponse;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 *
 * @author GfellerM01
 */
@WebServlet(name = "GatewayServlet", urlPatterns = {"/GatewayServlet"})
public class GatewayServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "getManga":
                System.out.println("getManga");
                break;
            case "getFavoris":
                System.out.println("getFavoris");
                break;
        }
    }

    public String apiGetManga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        return "";
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        response.setHeader("Access-Control-Allow-Origin", "*"); // pour le problème de CORS Policy
        PrintWriter out = response.getWriter();
        String result = "";
        String action = request.getParameter("action");

        switch (action) {
            case "apiLogin":
                result = apiLogin(request, response);
                out.println(result);
                break;

            case "apiAjoutManga":
                result = apiAjoutManga(request, response);
                out.println(result);
                break;

            case "apiAjoutFavoris":
                result = apiAjoutFavoris(request, response);
                out.println(result);
                break;
        }
    }

    public String sendCUD(String url, String method, HashMap<String, String> data) {
        String result = "false";
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(data));
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String apiLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valeurRetour = "";

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String code = sendCUD("http://gfellerm01.emf-informatique.ch/javaServiceRest1/webresources/user/login", "POST", new HashMap<String, String>() {
            {
                put("username", username);
                put("password", password);
            }
        });
        SimpleResponse status = new Gson().fromJson(code, SimpleResponse.class);
        System.out.println(status.getStatus());

        if ("success".equals(status.getStatus())) {
            // Login succeeded, do something here

            valeurRetour = "Vous etes connecte";

        } else if ("fail".equals(status)) {
            // Login failed, do something here

            valeurRetour = "Login Failed, try another username or password";
        } else {
            // Error occurred, do something here
            valeurRetour = "Login Error";
        }
        return valeurRetour;
    }

    public String apiAjoutManga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valeurRetour = "";
        
        String nomDuManga = request.getParameter("nomDuManga");
        String nomDuTome = request.getParameter("nomDuTome");
        String numDuTome = request.getParameter("numDuTome");
        String image = request.getParameter("image");
        
        String code = sendCUD("http://desousar.emf-informatique.ch/java.JspRestFull2/webresources/manga/ajoutManga", "POST", new HashMap<String, String>() {
            {
                put("nomDuManga", nomDuManga);
                put("nomDuTome", nomDuTome);
                put("numDuTome", numDuTome);
                put("image", image);
            }
        });
        SimpleResponse status = new Gson().fromJson(code, SimpleResponse.class);
        
        
        
        return valeurRetour;
    }

    public String apiAjoutFavoris(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String valeurRetour = "";
        
        String fkUser = request.getParameter("fkUser");
        String fkManga = request.getParameter("fkManga");
        
        String code = sendCUD("http://desousar.emf-informatique.ch/java.JspRestFull2/webresources/manga/ajoutFavoris", "POST", new HashMap<String, String>() {
            {
                put("fkUser", fkUser);
                put("fkManga", fkManga);
            }
        });
        SimpleResponse status = new Gson().fromJson(code, SimpleResponse.class);
        
        return valeurRetour;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
