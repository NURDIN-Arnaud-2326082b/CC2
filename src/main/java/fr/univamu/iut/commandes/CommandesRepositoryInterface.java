package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;

/**
 * Interface pour le dépôt de commandes.
 */
public interface CommandesRepositoryInterface {

    /**
     * Ferme la connexion au dépôt.
     */
    void close();

    /**
     * Récupère une commande par son identifiant.
     *
     * @param id_commande l'identifiant de la commande
     * @return la commande correspondante
     */
    Commandes getCommande(int id_commande);

    /**
     * Récupère toutes les commandes.
     *
     * @return une liste de toutes les commandes
     */
    ArrayList<Commandes> getAllCommandes();

    /**
     * Met à jour une commande.
     *
     * @param id_commande l'identifiant de la commande
     * @param prix_total le nouveau prix total de la commande
     * @param date_retrait la nouvelle date de retrait
     * @param localisation_retrait la nouvelle localisation de retrait
     * @param statut le nouveau statut de la commande
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean updateCommande(int id_commande, double prix_total, String date_retrait, String localisation_retrait, String statut);

    /**
     * Ajoute une nouvelle commande.
     *
     * @param commande la commande à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    boolean createCommande(Commandes commande);

    /**
     * Supprime une commande par son identifiant.
     *
     * @param id_commande l'identifiant de la commande
     * @return true si la suppression a réussi, false sinon
     */
    boolean deleteCommande(int id_commande);

    /**
     * Récupère le panier associé à une commande.
     *
     * @param id_commande l'identifiant de la commande
     * @return une liste des paniers de la commande
     */
    ArrayList<Panier> getPanierForCommande(int id_commande);
    
    /**
     * Ajoute un produit à une commande.
     *
     * @param commandeContient les informations sur le produit à ajouter
     * @return true si l'ajout a réussi, false sinon
     */
    boolean addCommandeContient(CommandeContient commandeContient);

    /**
     * Supprime un produit d'une commande.
     *
     * @param idCommande l'identifiant de la commande
     * @param idPanier l'identifiant du panier
     * @return true si la suppression a réussi, false sinon
     */
    boolean removeCommandeContient(int idCommande, int idPanier);

    /**
     * Met à jour la quantité d'un produit dans une commande.
     *
     * @param idCommande l'identifiant de la commande
     * @param idPanier l'identifiant du panier
     * @param quantite la nouvelle quantité
     * @return true si la mise à jour a réussi, false sinon
     */
    boolean updateCommandeContient(int idCommande, int idPanier, int quantite);

    /**
     * Calcule le prix total d'une commande.
     *
     * @param idCommande l'identifiant de la commande
     * @return le prix total de la commande
     */
    double getCommandeTotal(int idCommande);
}
