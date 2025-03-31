package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;

public interface CommandesRepositoryInterface {

    void close();

    Commandes getCommande(int id_commande);

    ArrayList<Commandes> getAllCommandes();

    boolean updateCommande(int id_commande, int id_client, int id_panier, double prix_total, String date_retrait, String localisation_retrait, String statut);

    boolean createCommande(Commandes commande);

    boolean deleteCommande(int id_commande);
}
