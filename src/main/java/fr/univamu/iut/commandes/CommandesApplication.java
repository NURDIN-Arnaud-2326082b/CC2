package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
@ApplicationScoped
public class CommandesApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface CommandesRepositoryInterface utilisée
     *          pour accéder aux données des commandes
     */
    @Produces
    private CommandesRepositoryInterface openDbConnection(){
        CommandesRepositoryMariadb db = null;

        try{
            db = new CommandesRepositoryMariadb("jdbc:mariadb://mysql-loeb.alwaysdata.net:3306/loeb", "loeb", "aC.2c2pxkzr4*qu");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param CommandesRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes CommandesRepositoryInterface CommandesRepo ) {
        CommandesRepo.close();
    }
}
