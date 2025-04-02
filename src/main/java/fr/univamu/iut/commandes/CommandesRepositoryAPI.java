package fr.univamu.iut.commandes;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;

public class CommandesRepositoryAPI implements CommandesRepositoryInterface {

    /**
     * URL de l'API des commandes
     */
    String url;

    /**
     * Constructeur initialisant l'url de l'API
     * @param url chaîne de caractères avec l'url de l'API
     */
    public CommandesRepositoryAPI(String url){
        this.url = url;
    }

    @Override
    public void close() {}

    @Override
    public Commandes getCommande(int id_commande) {
        Commandes myCommande = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget commandesResource  = client.target(url);
        // définition du point d'accès
        WebTarget commandesEndpoint = commandesResource.path("commandes/" + id_commande);
        // envoi de la requête et récupération de la réponse
        Response response = commandesEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si la commande a bien été trouvée, conversion du JSON en Commandes
        if(response.getStatus() == 200)
            myCommande = response.readEntity(Commandes.class);

        // fermeture de la connexion
        client.close();
        return myCommande;
    }

    @Override
    public ArrayList<Commandes> getAllCommandes() {
        ArrayList<Commandes> listCommandes = null;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget commandesResource  = client.target(url);
        // définition du point d'accès
        WebTarget commandesEndpoint = commandesResource.path("commandes");
        // envoi de la requête et récupération de la réponse
        Response response = commandesEndpoint.request(MediaType.APPLICATION_JSON).get();

        // si les commandes ont bien été trouvées, conversion du JSON en ArrayList<Commandes>
        if(response.getStatus() == 200)
            listCommandes = response.readEntity(new GenericType<ArrayList<Commandes>>() {});

        // fermeture de la connexion
        client.close();
        return listCommandes;
    }

    @Override
    public boolean updateCommande(int id_commande, double prix_total, String date_retrait, String localisation_retrait, String statut) {
        boolean result = false;

        Commandes updatedCommande = new Commandes(id_commande, prix_total, date_retrait, localisation_retrait, statut);

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget commandesResource  = client.target(url);
        // définition du point d'accès
        WebTarget commandesEndpoint = commandesResource.path("commandes/" + id_commande);
        // envoi de la requête avec la commande en JSON et récupération de la réponse
        Response response = commandesEndpoint.request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(updatedCommande, MediaType.APPLICATION_JSON));

        // si la mise à jour a été faite
        if(response.getStatus() == 200)
            result = true;

        // fermeture de la connexion
        client.close();

        return result;
    }

    @Override
    public boolean createCommande(Commandes commande) {
        boolean result = false;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget commandesResource  = client.target(url);
        // définition du point d'accès
        WebTarget commandesEndpoint = commandesResource.path("commandes");
        // envoi de la requête avec la commande en JSON et récupération de la réponse
        Response response = commandesEndpoint.request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(commande, MediaType.APPLICATION_JSON));

        // si la création a été faite
        if(response.getStatus() == 200)
            result = true;

        // fermeture de la connexion
        client.close();

        return result;
    }

    @Override
    public boolean deleteCommande(int id_commande) {
        boolean result = false;

        // création d'un client
        Client client = ClientBuilder.newClient();
        // définition de l'adresse de la ressource
        WebTarget commandesResource  = client.target(url);
        // définition du point d'accès
        WebTarget commandesEndpoint = commandesResource.path("commandes/" + id_commande);
        // envoi de la requête et récupération de la réponse
        Response response = commandesEndpoint.request(MediaType.APPLICATION_JSON).delete();

        // si la suppression a été faite
        if(response.getStatus() == 200)
            result = true;

        // fermeture de la connexion
        client.close();

        return result;
    }

    @Override
    public ArrayList<Panier> getPanierForCommande(int id_commande) {
        return null;
    }
}
