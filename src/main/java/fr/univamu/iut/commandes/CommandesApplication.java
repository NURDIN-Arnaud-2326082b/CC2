package fr.univamu.iut.commandes;

import fr.univamu.iut.commandes.CommandesRepositoryInterface;
import fr.univamu.iut.commandes.CommandesRepositoryMariadb;
import fr.univamu.iut.commandes.MariaDB;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationScoped
@ApplicationPath("/api")
public class CommandesApplication extends Application {

    private static final String PANIER_REST_URL = "http://localhost:9080/Panier-1.0-SNAPSHOT/api/Panier";

    @Produces
    @MariaDB
    @ApplicationScoped
    public CommandesRepositoryInterface openDbConnection() {
        CommandesRepositoryMariadb db = null;
        try {
            db = new CommandesRepositoryMariadb("jdbc:mariadb://mysql-loeb.alwaysdata.net/loeb_commandes", "loeb", "aC.2c2pxkzr4*qu");
            // Injection du client panier dans le repository
            if (db != null) {
                db.setPanierClient(createPanierClient());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }


    /**
     * Produit l'URL de l'API panier.
     *
     * @return l'URL de l'API panier
     */
    @Produces
    @PanierApiUrl
    @ApplicationScoped
    public String producePanierApiUrl() {
        return System.getProperty("panier.api.url", PANIER_REST_URL);
    }

    /**
     * Crée une instance de PanierClientInterface.
     *
     * @return une instance de PanierClientInterface
     */
    @Produces
    @ApplicationScoped
    public PanierClientInterface createPanierClient() {
        return new PanierClient(producePanierApiUrl());
    }

    /**
     * Ferme la connexion à la base de données.
     *
     * @param commandesRepo l'instance de CommandesRepositoryInterface à fermer
     */
    public void closeDbConnection(@Disposes @MariaDB CommandesRepositoryInterface commandesRepo) {
        if (commandesRepo != null) {
            commandesRepo.close();
        }
    }

    /**
     * Ferme le client panier.
     *
     * @param panierClient l'instance de PanierClientInterface à fermer
     */
    public void closePanierClient(@Disposes PanierClientInterface panierClient) {
        if (panierClient != null) {
            panierClient.close();
        }
    }
}
