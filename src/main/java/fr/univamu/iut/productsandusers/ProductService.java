package fr.univamu.iut.productsandusers;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;


/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class ProductService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les produit
     */
    protected ProductRepositoryInterface productRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param productRepo objet implémentant l'interface d'accès aux données
     */
    public ProductService(ProductRepositoryInterface productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * Méthode retournant les informations sur les produits au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllProductsJSON(){

        ArrayList<Product> allProducts = productRepo.getAllProducts();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allProducts);
        }
        catch (Exception e){
            System.err.println( e.getMessage() );
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un produit recherché
     * @param reference la référence du produit recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getProductJSON(String reference ){
        String result = null;
        Product myProduct = productRepo.getProduct(reference);

        // si le livre a été trouvé
        if( myProduct != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myProduct);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un produit
     * @param reference référence du produit à mettre à jours
     * @param product les nouvelles informations a être utilisées
     * @return true si le produit a pu être mis à jours
     */
    public boolean updateProduct(String reference, Product product) {
        return productRepo.updateProduct(reference, product.name, product.category, product.stock, product.unit, product.price);
    }

    /**
     * Méthode permettant de créer un nouveau produit
     * @param product le produit à créer
     * @return true si le produit a été créé avec succès
     */
    public boolean createProduct(Product product) {
        return productRepo.createProduct(product);
    }

    /**
     * Méthode permettant de supprimer un produit
     * @param reference la référence du produit à supprimer
     * @return true si le produit a été supprimé avec succès
     */
    public boolean deleteProduct(String reference) {
        return productRepo.deleteProduct(reference);
    }
}
