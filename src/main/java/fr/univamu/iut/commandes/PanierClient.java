package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * Classe cliente pour interagir avec le service REST du panier.
 */
@ApplicationScoped
public class PanierClient implements PanierClientInterface {
    
    private final String baseUrl;
    private final Client client;
    
    /**
     * Constructeur par défaut.
     */
    public PanierClient() {
        this(System.getProperty("panier.api.url", "http://localhost:8080/panier-1.0-SNAPSHOT/api/panier"));
    }
    
    /**
     * Constructeur avec l'URL de base.
     *
     * @param baseUrl l'URL de base du service panier
     */


    public PanierClient(@PanierApiUrl String baseUrl) {
        this.baseUrl = baseUrl;
        this.client = ClientBuilder.newClient();
    }



    @Override
    public double getPanierTotal(String idPanier) {
        WebTarget target = client.target(baseUrl).path(idPanier);
        try {
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == 200) {
                Object panier = response.readEntity(Object.class);
                return extractPrixTotal(panier);
            }
        } catch (Exception e) {
            System.err.println("Problème de connexion à l'API Panier: " + e.getMessage());
        }
        return 0.0;
    }
    
    /**
     * Extrait le prix total d'un objet panier.
     *
     * @param panier l'objet panier
     * @return le prix total du panier
     */
    private double extractPrixTotal(Object panier) {

        return 0.0;
    }
    
    @Override
    public Object getPanierById(String idPanier) {
        WebTarget target = client.target(baseUrl).path(idPanier);
        try {
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == 200) {
                return response.readEntity(Object.class);
            }
        } catch (Exception e) {
            System.err.println("Problème de connexion à l'API Panier: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public List<Object> getPanierProduits(String idPanier) {
        WebTarget target = client.target(baseUrl).path(idPanier).path("produits");
        try {
            Response response = target.request(MediaType.APPLICATION_JSON).get();
            if (response.getStatus() == 200) {
                return response.readEntity(new GenericType<List<Object>>(){});
            }
        } catch (Exception e) {
            System.err.println("Problème de connexion à l'API Panier: " + e.getMessage());
        }
        return null;
    }
    
    @Override
    public void close() {
        if (client != null) {
            client.close();
        }
    }
}

