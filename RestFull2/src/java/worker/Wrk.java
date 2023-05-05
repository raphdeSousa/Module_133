/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package worker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
                String url = "jdbc:mysql://desousar.emf-informatique.ch:3306/desousar_133_gestionmanga?serverTimezone=CET";
                //On essaie de se connecter à notre URL à partir d'un identifiant. 
                //Ici, le nom d'utilisateur est "desousar_root133",et le mot de pass est {Y[zROWnN;*& .
                //Si la connexion est réussie, la méthode "getConnection" renverra "true".
                jdbcConnection = DriverManager.getConnection(url, "desousar_root133", "{Y[zROWnN;*&");
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
                            String s = "";
                            s = "Pk du Manga : "+rs.getString(1)+" Nom du Manga " + rs.getString(2) + " Nom du Tome " + rs.getString(3) + " Numéro du Tome " + rs.getString(4) + " img " + rs.getString(5)+" \n";
                            resultat.add(s);
                            //On stocke notre String dans notre résultat final.
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

    public boolean addManga(String nomDuTome, String nomDuManga, String numeroDuTome, String image) {
        boolean ok = false;
        Statement stmt = null;
        ResultSet rs = null;
        if (dbConnect()) {
            PreparedStatement ps = null;
            try {
                //On essaie de créer une requête grâce à notre connexion. 
                // Create a SQL statement
                ps = jdbcConnection.prepareStatement("INSERT INTO t_manga (NomDuManga, NomDuTome, NumeroDuTome, Image) VALUES (?, ?, ?, ?)");
                // Create a PreparedStatement object
                ps.setString(1, nomDuManga);
                ps.setString(2, nomDuTome);
                ps.setString(3, numeroDuTome);
                ps.setString(4, image);
                // Execute the SQL statement
                int rowsInserted = ps.executeUpdate();
                ok = rowsInserted > 0;
                //On ferme le tout pour optimiser les performances.
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
                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ok;
    }

    public boolean modifyManga(int pk, String nomDuTome, String nomDuManga, String numeroDuTome, String image) {
        boolean ok = false;
        boolean opendb = dbConnect();
        ResultSet rs = null;
        if (opendb) {
            PreparedStatement ps = null;
            try {
                //On essaie de créer une requête grâce à notre connexion. 
                // Create a SQL statement
                ps = jdbcConnection.prepareStatement("Update t_manga SET NomDuManga = ?, NomDuTome = ?, NumeroDuTome = ?, Image=? WHERE PK_Manga = ?");
                // Create a PreparedStatement object
                ps.setString(1, nomDuManga);
                ps.setString(2, nomDuTome);
                ps.setString(3, numeroDuTome);
                ps.setString(4, image);
                ps.setInt(5, pk);
                ps.executeUpdate();
                ok = true;
                //On ferme le tout pour optimiser les performances.
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
                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return ok;
    }
    public boolean deleteManga(int pk) {
        boolean ok = false;
        boolean opendb = dbConnect();
        ResultSet rs = null;
        if (opendb) {
            PreparedStatement ps = null;
            try {
                //On essaie de créer une requête grâce à notre connexion. 
                // Create a SQL statement
                ps = jdbcConnection.prepareStatement("DELETE FROM t_manga WHERE PK_Manga = ?");
                // Create a PreparedStatement object
                ps.setInt(1, pk);
                ps.executeUpdate();
                ok = true;
                //On ferme le tout pour optimiser les performances.
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
                    if (ps != null) {
                        ps.close();
                        ps = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return ok;
    }

    public ArrayList<String> lireFavoris(int user) {
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
                    String sql = "SELECT tr_manga_user.FK_user, t_manga.NomDuManga, t_manga.NomDuTome, t_manga.NumeroDuTome, t_manga.Image FROM t_manga INNER JOIN tr_manga_user ON tr_manga_user.FK_manga = t_manga.PK_Manga WHERE tr_manga_user.FK_user = " + user;


                    //On exécute la requête et on stocke la réponse dans un "ResulSet"
                    //Si notre "ResulSet" contient quelque chose, c'est qu'on a reçu une réponse !
                    if ((rs = stmt.executeQuery(sql)) != null) {
                        //On effectue une boucle qui va parcourir notre résultat.
                        //La méthode ".next()" avance d'un index renvoie "true"
                        //s'il y a un résultat. Sinon, "false" quand il atteint
                        //un enregistrement null.
                        while (rs.next()) {
                            //On stocke le nom récupéré de la colonne "NomDuManga". 
                            String result = "";
                            result = result + "User " + user + " Nom du manga: " + rs.getString(2) + " Nom du tome: " + rs.getString(3) + " Numero du tome: " + rs.getString(4) + " Image:" + rs.getString(5);
                            resultat.add(result);
                        }

                        //On stocke notre String dans notre résultat final.
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

    public boolean addFavoris(int fkUser, int fkManga) {
        boolean ok = false;
        Statement stmt = null;
        ResultSet rs = null;
        if (dbConnect()) {
            PreparedStatement ps = null;
            try {
                //On essaie de créer une requête grâce à notre connexion. 
                // Create a SQL statement
                ps = jdbcConnection.prepareStatement("INSERT INTO tr_manga_user (FK_user, FK_manga) VALUES (?, ?)");
                // Create a PreparedStatement object
                ps.setInt(1, fkUser);
                ps.setInt(2, fkManga);
                // Execute the SQL statement
                int rowsInserted = ps.executeUpdate();
                ok = rowsInserted > 0;
                //On ferme le tout pour optimiser les performances.

            } catch (SQLException ex) {
                Logger.getLogger(Wrk.class
                        .getName()).log(Level.SEVERE, null, ex);
            } //On repasse les variables pour vérifier que tout est bien fermé.
            finally {
                dbDisconnect();
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (ps != null) {
                        ps.close();
                        ps = null;

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return ok;
    }

    public boolean modifyFavoris(int newFkUser, int newFkManga, int oldFkUser, int oldFkManga) {
        boolean ok = false;
        boolean opendb = dbConnect();
        ResultSet rs = null;
        if (opendb) {
            PreparedStatement ps = null;
            try {
                //On essaie de créer une requête grâce à notre connexion. 
                // Create a SQL statement
                ps = jdbcConnection.prepareStatement("Update tr_manga_user SET FK_user = ?, FK_manga = ? WHERE FK_manga = ? AND FK_user =?");
                // Create a PreparedStatement object
                ps.setInt(1, newFkUser);
                ps.setInt(2, newFkManga);
                ps.setInt(3, oldFkManga);
                ps.setInt(4, oldFkUser);
                ps.executeUpdate();
                ok = true;
                //On ferme le tout pour optimiser les performances.

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
                    Logger.getLogger(Wrk.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (ps != null) {
                        ps.close();
                        ps = null;

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return ok;
    }

    public boolean deleteFavoris(int fkUser, int fkManga) {
        boolean ok = false;
        boolean opendb = dbConnect();
        ResultSet rs = null;
        if (opendb) {
            PreparedStatement ps = null;
            try {
                //On essaie de créer une requête grâce à notre connexion. 
                // Create a SQL statement
                ps = jdbcConnection.prepareStatement("DELETE FROM tr_manga_user WHERE FK_manga = ? AND FK_user = ?");
                // Create a PreparedStatement object
                ps.setInt(1, fkManga);
                ps.setInt(2, fkUser);
                ps.executeUpdate();
                ok = true;
                //On ferme le tout pour optimiser les performances.

            } catch (SQLException ex) {
                Logger.getLogger(Wrk.class
                        .getName()).log(Level.SEVERE, null, ex);
            } //On repasse les variables pour vérifier que tout est bien fermé.
            finally {
                dbDisconnect();
                try {
                    if (rs != null) {
                        rs.close();
                        rs = null;

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (ps != null) {
                        ps.close();
                        ps = null;

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Wrk.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return ok;
    }

}
