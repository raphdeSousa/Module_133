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
        Boolean connecte = false;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        URL url = new URL("http://localhost:8080/ServiceRest1/webresources/user/login");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        conn.setDoOutput(true);
        PrintWriter out = new PrintWriter(conn.getOutputStream());
        String requestBody = "username=" + URLEncoder.encode(username, "UTF-8") + "&password=" + URLEncoder.encode(password, "UTF-8");
        out.print(requestBody);
        out.close();

        RequestDispatcher dispatch;

        if (response.getStatus() == 200) {
            String json;
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            json = sb.toString();

            SimpleResponse status = new Gson().fromJson(json, SimpleResponse.class);
            String result = status.getStatus();

            if ("success".equals(result)) {
                // Login succeeded, do something here
                connecte = true;
                BeanInfo info = new BeanInfo();
                info.setUsername(username);
                info.setPassword(password);

                dispatch = request.getRequestDispatcher("pageAutorise.jsp");
                System.out.println("sucess");

            } else if ("fail".equals(status)) {
                // Login failed, do something here

            } else {
                // Error occurred, do something here

            }
        } else {
            // gestion de l'erreur HTTP

        }
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
