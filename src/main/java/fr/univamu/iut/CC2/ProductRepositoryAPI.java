package fr.univamu.iut.CC2;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public class ProductRepositoryAPI implements ProductRepositoryInterface {

    /**
     * URL de l'API des livres
     */
    String url;

    /**
     * Constructeur initialisant l'url de l'API
     * @param url chaîne de caractères avec l'url de l'API
     */
    public ProductRepositoryAPI(String url){
        this.url = url ;
    }

    @Override
    public void close() {}

    @Override
    public Product getProduct(String reference) {
        Product myProduct = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget ProductResource  = client.target(url);
        // définition du point d'accès
        WebTarget ProductEndpoint = ProductResource.path("Products/"+reference);
        // envoi de la requête et récupération de la réponse
        Response response = ProductEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le livre a bien été trouvé, conversion du JSON en Product
        if( response.getStatus() == 200)
            myProduct = response.readEntity(Product.class);

        // fermeture de la connexion
        client.close();
        return myProduct;
    }

    @Override
    public boolean updateProduct(String reference, String title, String authors, char status) {
        boolean result = false ;

        Product updatedProduct = new Product(reference, title, authors);
        updatedProduct.setStatus( status ) ;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget ProductResource  = client.target(url);
        // définition du point d'accès
        WebTarget ProductEndpoint = ProductResource.path("Products/"+reference);
        // envoi de la requête avec le livre en JSON et récupération de la réponse
        Response response = ProductEndpoint.request(MediaType.APPLICATION_JSON)
                .put( Entity.entity(updatedProduct, MediaType.APPLICATION_JSON) );

        // si la mise à jour a été faite
        if( response.getStatus() == 200)
            result = true;

        // fermeture de la connexion
        client.close();

        return result;
    }
}