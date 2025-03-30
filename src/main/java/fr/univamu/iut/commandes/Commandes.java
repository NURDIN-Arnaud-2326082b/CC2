package fr.univamu.iut.commandes;

/**
 * Classe représentant une commande
 */
public class Commandes {

    protected int id_commande;
    protected int id_abonne;
    protected int id_panier;
    protected double prix_total;
    protected String date_commande;
    protected String date_retrait;
    protected String localisation_retrait;
    protected String statut;
    protected String moyen_paiement;

    public Commandes() {}

    public Commandes(int id_commande, int id_abonne, int id_panier, double prix_total, String date_commande, String date_retrait, String localisation_retrait, String statut, String moyen_paiement) {
        this.id_commande = id_commande;
        this.id_abonne = id_abonne;
        this.id_panier = id_panier;
        this.prix_total = prix_total;
        this.date_commande = date_commande;
        this.date_retrait = date_retrait;
        this.localisation_retrait = localisation_retrait;
        this.statut = statut;
        this.moyen_paiement = moyen_paiement;
    }

    // Getters et Setters pour chaque attribut
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_abonne() {
        return id_abonne;
    }

    public void setId_abonne(int id_abonne) {
        this.id_abonne = id_abonne;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public double getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(double prix_total) {
        this.prix_total = prix_total;
    }

    public String getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(String date_commande) {
        this.date_commande = date_commande;
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

    public String getMoyen_paiement() {
        return moyen_paiement;
    }

    public void setMoyen_paiement(String moyen_paiement) {
        this.moyen_paiement = moyen_paiement;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", id_abonne=" + id_abonne +
                ", id_panier=" + id_panier +
                ", prix_total=" + prix_total +
                ", date_commande='" + date_commande + '\'' +
                ", date_retrait='" + date_retrait + '\'' +
                ", localisation_retrait='" + localisation_retrait + '\'' +
                ", statut='" + statut + '\'' +
                ", moyen_paiement='" + moyen_paiement + '\'' +
                '}';
    }
}