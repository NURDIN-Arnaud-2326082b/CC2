package fr.univamu.iut.product;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;


/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class ProductService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les livres
     */
    protected ProductRepositoryInterface bookRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param bookRepo objet implémentant l'interface d'accès aux données
     */
    public ProductService(ProductRepositoryInterface bookRepo) {
        this.bookRepo = bookRepo;
    }

    /**
     * Méthode retournant les informations sur les livres au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllBooksJSON(){

        ArrayList<Product> allProducts = bookRepo.getAllProducts();

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
     * Méthode retournant au format JSON les informations sur un livre recherché
     * @param reference la référence du livre recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getBookJSON( String reference ){
        String result = null;
        Product myProduct = bookRepo.getProduct(reference);

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
     * Méthode permettant de mettre à jours les informations d'un livre
     * @param reference référence du livre à mettre à jours
     * @param product les nouvelles infromations a été utiliser
     * @return true si le livre a pu être mis à jours
     */
    public boolean updateBook(String reference, Product product) {
        return bookRepo.updateProduct(reference, product.title, product.authors, product.status);
    }
}
