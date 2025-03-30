package fr.univamu.iut.commandes;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;


/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class CommandesService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les livres
     */
    protected CommandesRepositoryInterface CommandesRepo ;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param CommandesRepo objet implémentant l'interface d'accès aux données
     */
    public CommandesService(CommandesRepositoryInterface CommandesRepo) {
        this.CommandesRepo = CommandesRepo;
    }

    /**
     * Méthode retournant les informations sur les livres au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllCommandessJSON(){

        ArrayList<Commandes> allCommandess = CommandesRepo.getAllCommandess();

        // création du json et conversion de la liste de livres
        String result = null;
        try( Jsonb jsonb = JsonbBuilder.create()){
            result = jsonb.toJson(allCommandess);
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
    public String getCommandesJSON( String reference ){
        String result = null;
        Commandes myCommandes = CommandesRepo.getCommandes(reference);

        // si le livre a été trouvé
        if( myCommandes != null ) {

            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myCommandes);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un livre
     * @param reference référence du livre à mettre à jours
     * @param Commandes les nouvelles infromations a été utiliser
     * @return true si le livre a pu être mis à jours
     */
    public boolean updateCommandes(String reference, Commandes Commandes) {
        return CommandesRepo.updateCommandes(reference, Commandes.title, Commandes.authors, Commandes.status);
    }
}
