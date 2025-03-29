package fr.univamu.iut.CC2;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

/**
 * Classe utilisée pour récupérer les informations nécessaires à la ressource
 * (permet de dissocier ressource et mode d'éccès aux données)
 */
public class PanierService {

    /**
     * Objet permettant d'accéder au dépôt où sont stockées les informations sur les livres
     */
    protected PanierRepositoryInterface PanierRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param PanierRepo objet implémentant l'interface d'accès aux données
     */
    public PanierService(PanierRepositoryInterface PanierRepo) {
        this.PanierRepo = PanierRepo;
    }

    /**
     * Méthode retournant les informations sur les livres au format JSON
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getAllPanierJSON() {
        ArrayList<Panier> allPaniers = PanierRepo.getAllPanier();

        // création du json et conversion de la liste de livres
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allPaniers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    /**
     * Méthode retournant au format JSON les informations sur un livre recherché
     * @param idPanier la référence du livre recherché
     * @return une chaîne de caractère contenant les informations au format JSON
     */
    public String getPanierJSON(int idPanier) {
        String result = null;
        Panier myPanier = PanierRepo.getPanier(idPanier);

        // si le livre a été trouvé
        if (myPanier != null) {
            // création du json et conversion du livre
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myPanier);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    /**
     * Méthode permettant de mettre à jours les informations d'un livre
     * @param idPanier référence du livre à mettre à jours
     * @param panier les nouvelles infromations a été utiliser
     * @return true si le livre a pu être mis à jours
     */
    public boolean updatePanier(int idPanier, Panier panier) {
        return PanierRepo.UpdatePanier(idPanier, panier.nbreArticle, panier.nomArticle, panier.status);
    }
}