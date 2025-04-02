package fr.univamu.iut.CC2;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

public class PanierRepositoryAPI implements PanierRepositoryInterface {

    /**
     * URL de l'API des livres
     */
    String url;

    /**
     * Constructeur initialisant l'url de l'API
     * @param url chaîne de caractères avec l'url de l'API
     */
    public PanierRepositoryAPI(String url){
        this.url = url ;
    }

    @Override
    public void close() {}

    @Override
    public Panier getPanier(int IdPanier) {
        return null;
    }

    @Override
    public ArrayList<Panier> getAllPanier() {
        return null;
    }

    @Override
    public boolean UpdatePanier(int IdPanier, int nbreArticle, String nomArticle, int IdClient) {
        return false;
    }


    public Panier getpanier(String nomArticle) {
        Panier mypanier = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget panierResource  = client.target(url);
        // définition du point d'accès
        WebTarget panierEndpoint = panierResource.path("paniers/"+nomArticle);
        // envoi de la requête et récupération de la réponse
        Response response = panierEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si le livre a bien été trouvé, conversion du JSON en panier
        if( response.getStatus() == 200)
            mypanier = response.readEntity(Panier.class);

        // fermeture de la connexion
        client.close();
        return mypanier;
    }


    public boolean updatepanier(String nomArticle, int IdClient, int nbreArticle, int IdPanier) {
        boolean result = false ;

        Panier updatedpanier = new Panier(nomArticle, nbreArticle, IdPanier);
        updatedpanier.setIdClient( IdClient) ;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget panierResource  = client.target(url);
        // définition du point d'accès
        WebTarget panierEndpoint = panierResource.path("paniers/"+nomArticle);
        // envoi de la requête avec le livre en JSON et récupération de la réponse
        Response response = panierEndpoint.request(MediaType.APPLICATION_JSON)
                .put( Entity.entity(updatedpanier, MediaType.APPLICATION_JSON) );

        // si la mise à jour a été faite
        if( response.getStatus() == 200)
            result = true;

        // fermeture de la connexion
        client.close();

        return result;
    }
}