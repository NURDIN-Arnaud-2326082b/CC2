package fr.univamu.iut.productsandusers;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class UsersRepositoryMariadb implements UserRepositoryInterface, Closeable {

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
    public UsersRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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
    public User getUser(int id) {

        User selectedUser = null;

        String query = "SELECT * FROM User WHERE idClient=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, String.valueOf(id));

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                String email = result.getString("email");
                String firstName = result.getString("firstName");
                String name = result.getString("name");
                String password = result.getString("password");
                User.Role role = User.Role.valueOf(result.getString("role"));

                // création et initialisation de l'objet User
                selectedUser = new User(id, email, firstName, name, password, role);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers;

        String query = "SELECT * FROM User";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listUsers = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                int id = result.getInt("idClient");
                String email = result.getString("email");
                String firstName = result.getString("firstName");
                String name = result.getString("name");
                String password = result.getString("password");
                User.Role role = User.Role.valueOf(result.getString("role"));

                // création de l'utilisateur courant
                User currentUser = new User(id, email, firstName, name, password, role);

                listUsers.add(currentUser);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public boolean updateUser(int id, String email, String firstName, String name, String password, String role) {
        String query = "UPDATE User SET email=?, firstName=?, name=?, password=?, role=? where idClient=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, email);
            ps.setString(2, firstName);
            ps.setString(3, name);
            ps.setString(4, password);
            ps.setString(5, role);
            ps.setInt(6, id);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }

    @Override
    public void createUser(User user) {
        String query = "INSERT INTO User (email, firstName, name, password, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getName());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole().name());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String query = "DELETE FROM User WHERE idClient=?";
        int nbRowModified = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);

            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowModified != 0);
    }
}

