package fr.univamu.iut.productsandusers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;


@ApplicationPath("/api")
@ApplicationScoped
public class ProductApplication extends Application {

    /**
     * Méthode appelée par l'API CDI pour injecter la connection à la base de données au moment de la création
     * de la ressource
     * @return un objet implémentant l'interface BookRepositoryInterface utilisée
     *          pour accéder aux données des livres, voire les modifier
     */
    @Produces
    private ProductRepositoryInterface openDbConnection(){
        ProductsRepositoryMariadb db = null;

        try{
            db = new ProductsRepositoryMariadb("jdbc:mariadb://mysql-cc2.alwaysdata.net/cc2_produit_et_service", "cc2_library", "cc2r401");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    /**
     * Méthode permettant de fermer la connexion à la base de données lorsque l'application est arrêtée
     * @param productRepo la connexion à la base de données instanciée dans la méthode @openDbConnection
     */
    private void closeDbConnection(@Disposes ProductRepositoryInterface productRepo ) {
        productRepo.close();
    }

    @Produces
    private UserRepositoryInterface openUserDbConnection(){
        UsersRepositoryMariadb db = null;

        try{
            db = new UsersRepositoryMariadb("jdbc:mariadb://mysql-cc2.alwaysdata.net/cc2_produit_et_service", "cc2_library", "cc2r401");
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        return db;
    }

    private void closeUserDbConnection(@Disposes UserRepositoryInterface userRepo ) {
        userRepo.close();
    }
}
