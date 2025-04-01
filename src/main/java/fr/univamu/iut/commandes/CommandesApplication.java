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

    @Produces
    @MariaDB
    private CommandesRepositoryInterface openDbConnection() {
        CommandesRepositoryMariadb db = null;
        try {
            db = new CommandesRepositoryMariadb("jdbc:mariadb://mysql-loeb.alwaysdata.net/loeb_commandes", "loeb", "aC.2c2pxkzr4*qu");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return db;
    }

    private void closeDbConnection(@Disposes @MariaDB CommandesRepositoryInterface commandesRepo) {
        commandesRepo.close();
    }
/**
 * Méthode appelée par l'API CDI pour injecter l'API Commandes au moment de la création de la ressource
 * @return une instance de l'API avec l'url à utiliser
 */
    @Produces
    private CommandesRepositoryInterface connectCommandesApi(){
        return new CommandesRepositoryAPI("http://localhost:8080/book-1.0-SNAPSHOT/api/");
    }


}
