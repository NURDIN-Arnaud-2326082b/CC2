package fr.univamu.iut.productsandusers;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe permettant d'accèder aux livres stockés dans une base de données Mariadb
 */
public class ProductsRepositoryMariadb implements ProductRepositoryInterface, Closeable {

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
    public ProductsRepositoryMariadb(String infoConnection, String user, String pwd ) throws java.sql.SQLException, java.lang.ClassNotFoundException {
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
    public Product getProduct(String reference) {

        Product selectedProduct = null;

        String query = "SELECT * FROM Product WHERE reference=?";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, reference);

            // exécution de la requête
            ResultSet result = ps.executeQuery();

            // récupération du premier (et seul) tuple résultat
            // (si la référence du livre est valide)
            if( result.next() )
            {
                String name = result.getString("name");
                String category = result.getString("category");
                int stock = result.getInt("stock");
                String unit = result.getString("unite");
                float price = result.getFloat("prix");

                // création et initialisation de l'objet Book
                selectedProduct = new Product(reference, name, category, unit, price);
                selectedProduct.setStock(stock);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedProduct;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> listProducts;

        String query = "SELECT * FROM Product";

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            // exécution de la requête
            ResultSet result = ps.executeQuery();

            listProducts = new ArrayList<>();

            // récupération du premier (et seul) tuple résultat
            while ( result.next() )
            {
                String reference = result.getString("reference");
                String name = result.getString("name");
                String category = result.getString("category");
                int stock = result.getInt("stock");
                String unit = result.getString("unite");
                float price = result.getFloat("prix");

                // création du livre courant
                Product currentProduct = new Product(reference, name, category, unit, price);
                currentProduct.setStock(stock);

                listProducts.add(currentProduct);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProducts;
    }

    @Override
    public boolean updateProduct(String reference, String name, String category, int stock, String unit, float price) {
        String query = "UPDATE Product SET name=?, category=?, stock=?, unite=?, prix=?  where reference=?";
        int nbRowModified = 0;

        // construction et exécution d'une requête préparée
        try ( PreparedStatement ps = dbConnection.prepareStatement(query) ){
            ps.setString(1, name);
            ps.setString(2, category);
            ps.setInt(3, stock);
            ps.setString(4, unit);
            ps.setFloat(5, price);
            ps.setString(6, reference);

            // exécution de la requête
            nbRowModified = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ( nbRowModified != 0 );
    }
}
