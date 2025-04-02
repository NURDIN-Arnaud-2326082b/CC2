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

    /**
     * Permet de fermer la connection à la bd
     */
    @Override
    public void close() {
        try{
            dbConnection.close();
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
    }

    /**
     * permet de récupérer un produit à un identifiant donné
     * @param reference identifiant du produit recherché
     * @return le produit en question
     */
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

    /**
     * permet de récupérer tous les produits
     * @return la liste de tous les produits
     */
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

    /**
     *
     * @param reference identifiant du produit à mettre à jours
     * @param name nouveau nom du produit
     * @param category nouvelle categorie pour le produit
     * @param stock nouveau stock du produit
     * @param unit nouvelle unité du produit
     * @param price nouveau prix du produit
     * @return true si la mise à jour se fait false sinon
     */
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

    /**
     * Méthode permettant de créer un nouveau produit
     * @param product le produit à créer
     * @return true si le produit a été créé avec succès, false sinon
     */
    @Override
    public boolean createProduct(Product product) {
        String query = "INSERT INTO Product (reference, name, category, stock, unite, prix) VALUES (?, ?, ?, ?, ?, ?)";
        int nbRowInserted = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, product.getReference());
            ps.setString(2, product.getName());
            ps.setString(3, product.getCategory());
            ps.setInt(4, product.getStock());
            ps.setString(5, product.getUnit());
            ps.setFloat(6, product.getPrice());

            nbRowInserted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowInserted != 0);
    }

    /**
     * Méthode permettant de supprimer un produit
     * @param reference la référence du produit à supprimer
     * @return true si le produit a été supprimé avec succès, false sinon
     */
    @Override
    public boolean deleteProduct(String reference) {
        String query = "DELETE FROM Product WHERE reference=?";
        int nbRowDeleted = 0;

        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, reference);

            nbRowDeleted = ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return (nbRowDeleted != 0);
    }
}
