package fr.univamu.iut.commandes;

import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.NotFoundException;

import java.util.ArrayList;

public class CommandesService {

    protected CommandesRepositoryInterface commandesRepo;

    /**
     * Constructeur permettant d'injecter l'accès aux données
     * @param commandesRepo objet implémentant l'interface d'accès aux données des commandes
     */
    @Inject
    public CommandesService(CommandesRepositoryInterface commandesRepo) {
        this.commandesRepo = commandesRepo;
    }

    public ArrayList<Commandes> getAllCommandes() {
        return commandesRepo.getAllCommandes();
    }

    public Commandes getCommandeById(int id_commande) {
        return commandesRepo.getCommande(id_commande);
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
        return commandesRepo.updateCommande(id_commande, commande.getPrix_total(), commande.getDate_retrait(), commande.getLocalisation_retrait(), commande.getStatut());
    }
    
    public boolean addCommande(Commandes commande) {
        return commandesRepo.createCommande(commande);
    }

    public boolean createCommande(Commandes commande) {
        return commandesRepo.createCommande(commande);
    }

    public boolean removeCommande(int id_commande) {
        return commandesRepo.deleteCommande(id_commande);
    }

    public boolean deleteCommande(int id_commande) {
        return commandesRepo.deleteCommande(id_commande);
    }

    public ArrayList<Panier> getCommandeContenu(int id_commande) {
        return commandesRepo.getPanierForCommande(id_commande);
    }

    public String getPanierForCommande(int id_commande) {
        ArrayList<Panier> panier = commandesRepo.getPanierForCommande(id_commande);
        String result = null;
        try (Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(panier);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    
    public boolean addCommandeContient(CommandeContient commandeContient) {
        return commandesRepo.addCommandeContient(commandeContient);
    }
    
    public boolean updateCommandeContient(int idCommande, int idPanier, int quantite) {
        return commandesRepo.updateCommandeContient(idCommande, idPanier, quantite);
    }
    
    public boolean removeCommandeContient(int idCommande, int idPanier) {
        return commandesRepo.removeCommandeContient(idCommande, idPanier);
    }
    
    public double getCommandeTotal(int idCommande) {
        return commandesRepo.getCommandeTotal(idCommande);
    }

    /**
     * Méthode permettant d'enregistrer une commande
     * @param id identifiant de la commande
     * @param commande objet Commandes contenant les détails de la commande
     * @return true si la commande a pu être enregistrée, false sinon
     */
    public boolean registerCommande(int id, Commandes commande) {
        boolean result = false;

        // récupération des informations de la commande
        Commandes existingCommande = commandesRepo.getCommande(id);

        // si la commande n'est pas trouvée
        if (existingCommande == null)
            throw new NotFoundException("Commande non trouvée");

        // si la commande est disponible
        if ("disponible".equals(existingCommande.getStatut())) {
            // mise à jour de la commande dans le dépôt
            result = commandesRepo.updateCommande(id, commande.getPrix_total(), commande.getDate_retrait(), commande.getLocalisation_retrait(), "enregistrée");

            // enregistrement de la commande
            if (result)
                result = commandesRepo.createCommande(commande);
        }
        return result;
    }
}
