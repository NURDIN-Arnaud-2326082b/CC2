package fr.univamu.iut.commandes;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class CommandesService {

    protected CommandesRepositoryInterface commandesRepo;

    public CommandesService(CommandesRepositoryInterface commandesRepo) {
        this.commandesRepo = commandesRepo;
    }

    public String getAllCommandesJSON() {
        ArrayList<Commandes> allCommandes = commandesRepo.getAllCommandes();
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allCommandes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getCommandeJSON(int id_commande) {
        String result = null;
        Commandes commande = commandesRepo.getCommande(id_commande);
        if (commande != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(commande);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public boolean updateCommande(int id_commande, Commandes commande) {
        return commandesRepo.updateCommande(id_commande, commande.getId_abonne(), commande.getId_panier(), commande.getPrix_total(), commande.getDate_commande(), commande.getDate_retrait(), commande.getLocalisation_retrait(), commande.getStatut(), commande.getMoyen_paiement());
    }

    public boolean createCommande(Commandes commande) {
        return commandesRepo.createCommande(commande);
    }

    public boolean deleteCommande(int id_commande) {
        return commandesRepo.deleteCommande(id_commande);
    }
}