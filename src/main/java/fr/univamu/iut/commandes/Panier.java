package fr.univamu.iut.commandes;

/**
 * Classe représentant un panier
 */
public class Panier {

    protected int id_commande;
    protected int id_panier;
    protected int quantite;

    public Panier() {}

    public Panier(int id_commande, int id_panier, int quantite) {
        this.id_commande = id_commande;
        this.id_panier = id_panier;
        this.quantite = quantite;
    }

    // Getters et Setters pour chaque attribut
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_commande=" + id_commande +
                ", id_panier=" + id_panier +
                ", quantite=" + quantite +
                '}';
    }
}
