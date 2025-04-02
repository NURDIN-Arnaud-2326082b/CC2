package fr.univamu.iut.commandes;

import java.util.List;

/**
 * Interface pour le client panier.
 */
public interface PanierClientInterface {
    
    /**
     * Récupère le prix total d'un panier.
     *
     * @param idPanier l'identifiant du panier
     * @return le prix total du panier
     */
    double getPanierTotal(String idPanier);
    
    /**
     * Récupère un panier par son identifiant.
     *
     * @param idPanier l'identifiant du panier
     * @return l'objet panier correspondant
     */
    Object getPanierById(String idPanier);
    
    /**
     * Récupère les produits contenus dans un panier.
     *
     * @param idPanier l'identifiant du panier
     * @return la liste des produits dans le panier
     */
    List<Object> getPanierProduits(String idPanier);
    
    /**
     * Ferme le client panier.
     */
    void close();
}
