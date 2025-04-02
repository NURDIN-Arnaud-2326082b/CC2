package fr.univamu.iut.commandes;

/**
 * Classe représentant la relation entre une commande et un panier.
 */
public class CommandeContient {
    private int idCommande;
    private int idPanier;
    private int quantite;

    /**
     * Constructeur par défaut.
     */
    public CommandeContient() {
    }

    /**
     * Constructeur avec tous les paramètres.
     *
     * @param idCommande l'identifiant de la commande
     * @param idPanier l'identifiant du panier
     * @param quantite la quantité commandée
     */
    public CommandeContient(int idCommande, int idPanier, int quantite) {
        this.idCommande = idCommande;
        this.idPanier = idPanier;
        this.quantite = quantite;
    }

    /**
     * Récupère l'identifiant de la commande.
     *
     * @return l'identifiant de la commande
     */
    public int getIdCommande() {
        return idCommande;
    }

    /**
     * Définit l'identifiant de la commande.
     *
     * @param idCommande l'identifiant de la commande à définir
     */
    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    /**
     * Récupère l'identifiant du panier.
     *
     * @return l'identifiant du panier
     */
    public int getIdPanier() {
        return idPanier;
    }

    /**
     * Définit l'identifiant du panier.
     *
     * @param idPanier l'identifiant du panier à définir
     */
    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    /**
     * Récupère la quantité.
     *
     * @return la quantité
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Définit la quantité.
     *
     * @param quantite la quantité à définir
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
