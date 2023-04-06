/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                String url = "jdbc:mysql://localhost:3306/desousar_133_gestionmanga?serverTimezone=CET";
                //On essaie de se connecter à notre URL à partir d'un identifiant. 
                //Ici, le nom d'utilisateur est "root", et il n'y a pas de mot de passe.
                //Si la connexion est réussie, la méthode "getConnection" renverra "true".
                jdbcConnection = DriverManager.getConnection(url, "desousar_root133", "{I)B~#SHdj#X");
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

    public ArrayList<String> lireManga() {
        //On prépare nos variables.
        ArrayList<String> resultat = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        //On essaie de se connecter à la base de données. 
        if (dbConnect()) {
            try {
                //On essaie de créer une requête grâce à notre connexion. 
                if ((stmt = jdbcConnection.createStatement()) != null) {
                    //Ce string est la requête SQL qui va récupérer les enregistrements.
                    String sql = "SELECT * FROM t_manga";
                    //On exécute la requête et on stocke la réponse dans un "ResulSet"
                    //Si notre "ResulSet" contient quelque chose, c'est qu'on a reçu une réponse !
                    if ((rs = stmt.executeQuery(sql)) != null) {
                        //On effectue une boucle qui va parcourir notre résultat.
                        //La méthode ".next()" avance d'un index renvoie "true"
                        //s'il y a un résultat. Sinon, "false" quand il atteint
                        //un enregistrement null.
                        while (rs.next()) {
                            //On stocke le nom récupéré de la colonne "NomDuManga". 
                            String s = rs.getString("PK_Manga");
                            //On stocke notre String dans notre résultat final.
                            resultat.add(s);
                        }
                    }
                    //On ferme le tout pour optimiser les performances.
                    rs.close();
                    rs = null;
                    stmt.close();
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
            } //On repasse les variables pour vérifier que tout est bien fermé.
            finally {
                dbDisconnect();
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (stmt != null) {
                        stmt.close();
                        stmt = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return resultat;
    }

}
