package fr.univamu.iut.CC2;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class PanierRepositoryMariadb implements PanierRepositoryInterface, Closeable {

    /**
     * Accès à la base de données (session)
     */
    protected Connection dbConnection ;

    /**
     * Constructeur de la classe
     * @param infoConnection chaîne de caractères avec les informations de connexion
     *                       (p.ex. jdbc:mariadb://mysql-[compte].alwaysdata.net/[compte]_library_db
     * @param user chaîne de caractères contenant l'identifiant de connexion à la base de données
     * @param pwd chaîne de caractères contenant le mot de passe à utiliser
     */
    public PanierRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection( infoConnection, user, pwd ) ;
    }

    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Panier getIdPanier(String IdPanier) {
        return null;
    }


    public Panier getPanier(int IdPanier) {
        return null;
    }

    @Override
    public ArrayList<Panier> getAllPanier() {
        ArrayList<Panier> listPaniers;

        String query = "SELECT * FROM panier";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listPaniers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                int IdPanier = result.getInt("IdPanier");
                int nbreArticle = result.getInt("nbreArticle");
                String nomArticle = result.getString("nomArticle");
                char status = result.getString("status").charAt(0);

                // création du livre courant
                Panier currentPanier = new Panier(IdPanier, nbreArticle, nomArticle);
                currentPanier.setStatus(status);

                listPaniers.add(currentPanier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listPaniers;
    }

    @Override
    public boolean UpdatePanier(int IdPanier, int nbreArticle, String nomArticle, char status) {
        return false;
    }


    public Panier getIdPanier(int IdPanier) {

        Panier selectedPanier = null;

        String query = "SELECT * FROM Book WHERE reference=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, IdPanier);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                int nbreArticle = result.getInt("nbreArticle");
                String nomArticle = result.getString("nomArticle");
                char status = result.getString("status").charAt(0);

                // création et initialisation de l'objet Book
                selectedPanier = new Panier(IdPanier, nbreArticle, nomArticle);
                selectedPanier.setStatus(status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedPanier;
    }



    public boolean updatePanier(int IdPanier, int nbreArticle, String nomArticle, char status) {
        String query = "UPDATE Book SET nomArticle=?, authors=?, status=?  where reference=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setInt(1, IdPanier);
            ps.setString(2, nomArticle);
            ps.setString(3, String.valueOf(status) );
            ps.setInt(4, nbreArticle);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}