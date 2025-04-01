package fr.univamu.iut.commandes;

/**
 * Classe représentant une commande
 */
public class Commandes {

    protected int id_commande;
    protected double prix_total;
    protected String date_retrait;
    protected String localisation_retrait;
    protected String statut;

    public Commandes() {}

    public Commandes(int id_commande, double prix_total, String date_retrait, String localisation_retrait, String statut) {
        this.id_commande = id_commande;
        this.prix_total = prix_total;
        this.date_retrait = date_retrait;
        this.localisation_retrait = localisation_retrait;
        this.statut = statut;
    }

    // Getters et Setters pour chaque attribut
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    public String getDate_retrait() {
        return date_retrait;
    }

    public void setDate_retrait(String date_retrait) {
        this.date_retrait = date_retrait;
    }

    public String getLocalisation_retrait() {
        return localisation_retrait;
    }

    public void setLocalisation_retrait(String localisation_retrait) {
        this.localisation_retrait = localisation_retrait;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", prix_total=" + prix_total +
                ", date_retrait='" + date_retrait + '\'' +
                ", localisation_retrait='" + localisation_retrait + '\'' +
                ", statut='" + statut + '\'' +
                '}';
    }
}
