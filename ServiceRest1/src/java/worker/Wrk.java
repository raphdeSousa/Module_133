/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package worker;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author desousar
 */
public class Wrk {

    private Connection jdbcConnection;

    public Wrk() {
        jdbcConnection = null;
    }

    private boolean dbConnect() {
        boolean ok = false;
        try {
            if (jdbcConnection == null) {
                //On spécifie que notre driver est JDBC
                Class.forName("com.mysql.jdbc.Driver");
                //L'URL se compose de l'adresse de notre base de données, ainsi que
                //la base de données à utiliser.
                String url = "jdbc:mysql://gfellerm01.emf-informatique.ch:3306/gfellerm01_133-Projet-Utilisateurs?serverTimezone=CET";
                //On essaie de se connecter à notre URL à partir d'un identifiant. 
                //Ici, le nom d'utilisateur est "root", et il n'y a pas de mot de passe.
                //Si la connexion est réussie, la méthode "getConnection" renverra "true".
                jdbcConnection = DriverManager.getConnection(url, "gfellerm01_133-Project", "Pa$$w0rdena");
                ok = true;
            }
        } catch (SQLException b) {
            Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, b);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Si la connexion s'est bien passée, on renvoie "true", sinon "false".
        return ok;
    }

    private boolean dbDisconnect() {
        boolean ok = false;
        // On vérifie si une connexion est toujours présente (donc pas nulle)
        if (jdbcConnection != null) {
            try {
                // On essaie de fermer la connexion, puis "vide" la variable.
                jdbcConnection.close();
                jdbcConnection = null;
                ok = true;
            } catch (SQLException ex) {
                Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ok;
    }

    public ArrayList<String> getUser(int PK) {
        ArrayList<String> lstUser = null;
        boolean result = dbConnect();
        if (result) {
            System.out.println("connection ok");
            PreparedStatement ps = null;
            String user = "";
            lstUser = new ArrayList<String>();
            try {
                ps = jdbcConnection.prepareStatement("SELECT * FROM t_user where PK_User = " + PK + ";");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    user = (String) rs.getString(1);
                    user += "," + (String) rs.getString(2);

                    lstUser.add(user);
                }
                rs.close();
                result = true;
                System.out.println("OK");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            if (result) {
                result = dbDisconnect();
            }
        }
        return lstUser;
    }

    public boolean checkLogin(String username, String password) {
        boolean success = false;
        boolean result = dbConnect();
        if (result) {
            try {
                PreparedStatement ps = jdbcConnection.prepareStatement("SELECT * FROM t_user WHERE Username = ?");
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String hashedPassword = rs.getString("Password").toUpperCase();
                    // On calcule le hash SHA256 du mot de passe fourni par l'utilisateur
                    String inputHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString().toUpperCase();
                    if (hashedPassword.equals(inputHash)) {
                        // Si les mots de passe correspondent, la connexion est réussie
                        success = true;
                    }
                }
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                result = dbDisconnect();
            }
        }
        return success;
    }

}
