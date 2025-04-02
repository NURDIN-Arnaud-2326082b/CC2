package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

/**
 * Classe ressource pour gérer les requêtes HTTP liées aux commandes.
 */
@Path("/commandes")
@ApplicationScoped
public class CommandesResource {

    private CommandesService service;

    public CommandesResource() {}

    @Inject
    public CommandesResource(@MariaDB CommandesRepositoryInterface commandesRepo) {
        this.service = new CommandesService(commandesRepo);
    }

    /**
     * Récupère toutes les commandes.
     *
     * @return une réponse contenant la liste de toutes les commandes
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCommandes() {
        ArrayList<Commandes> commandes = service.getAllCommandes();
        return Response.ok(commandes).build();
    }

    /**
     * Récupère une commande par son identifiant.
     *
     * @param id_commande l'identifiant de la commande
     * @return une réponse contenant la commande
     */
    @GET
    @Path("{id_commande}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommande(@PathParam("id_commande") int id_commande) {
        Commandes commande = service.getCommandeById(id_commande);
        if (commande == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande non trouvée").build();
        }
        return Response.ok(commande).build();
    }

    /**
     * Récupère le panier associé à une commande.
     *
     * @param id_commande l'identifiant de la commande
     * @return une réponse contenant le panier de la commande
     */
    @GET
    @Path("{id_commande}/panier")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPanierForCommande(@PathParam("id_commande") int id_commande) {
        ArrayList<Panier> panier = service.getCommandeContenu(id_commande);
        if (panier == null || panier.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("Aucun panier trouvé pour cette commande").build();
        }
        return Response.ok(panier).build();
    }

    /**
     * Met à jour une commande existante.
     *
     * @param id_commande l'identifiant de la commande à mettre à jour
     * @param commande la commande mise à jour
     * @return une réponse indiquant le résultat de l'opération
     */
    @PUT
    @Path("{id_commande}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCommande(@PathParam("id_commande") int id_commande, Commandes commande) {
        if (!service.updateCommande(id_commande, commande)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande non trouvée").build();
        }
        return Response.ok("Commande mise à jour").build();
    }

    /**
     * Ajoute une nouvelle commande.
     *
     * @param commande la commande à ajouter
     * @return une réponse indiquant le résultat de l'opération
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCommande(Commandes commande) {
        if (service.addCommande(commande)) {
            return Response.status(Response.Status.CREATED).entity("Commande ajoutée").build();
        }
        return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout de la commande").build();
    }

    /**
     * Supprime une commande par son identifiant.
     *
     * @param id_commande l'identifiant de la commande à supprimer
     * @return une réponse indiquant le résultat de l'opération
     */
    @DELETE
    @Path("{id_commande}")
    public Response deleteCommande(@PathParam("id_commande") int id_commande) {
        if (!service.removeCommande(id_commande)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Commande non trouvée").build();
        }
        return Response.ok("Commande supprimée").build();
    }

    /**
     * Ajoute du contenu à une commande.
     *
     * @param id_commande l'identifiant de la commande
     * @param commandeContient le contenu à ajouter
     * @return une réponse indiquant le résultat de l'opération
     */
    @POST
    @Path("{id_commande}/contenu")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCommandeContient(@PathParam("id_commande") int id_commande, CommandeContient commandeContient) {
        commandeContient.setIdCommande(id_commande);
        if (!service.addCommandeContient(commandeContient)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Erreur lors de l'ajout du contenu").build();
        }
        return Response.status(Response.Status.CREATED).entity("Contenu ajouté").build();
    }

    /**
     * Met à jour le contenu d'une commande.
     *
     * @param id_commande l'identifiant de la commande
     * @param idPanier l'identifiant du panier
     * @param commandeContient le contenu mis à jour
     * @return une réponse indiquant le résultat de l'opération
     */
    @PUT
    @Path("{id_commande}/contenu/{idPanier}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCommandeContient(@PathParam("id_commande") int id_commande,
                                           @PathParam("idPanier") int idPanier,
                                           CommandeContient commandeContient) {
        if (!service.updateCommandeContient(id_commande, idPanier, commandeContient.getQuantite())) {
            return Response.status(Response.Status.NOT_FOUND).entity("Contenu non trouvé").build();
        }
        return Response.ok("Contenu mis à jour").build();
    }

    /**
     * Supprime le contenu d'une commande.
     *
     * @param id_commande l'identifiant de la commande
     * @param idPanier l'identifiant du panier
     * @return une réponse indiquant le résultat de l'opération
     */
    @DELETE
    @Path("{id_commande}/contenu/{idPanier}")
    public Response deleteCommandeContient(@PathParam("id_commande") int id_commande,
                                           @PathParam("idPanier") int idPanier) {
        if (!service.removeCommandeContient(id_commande, idPanier)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Contenu non trouvé").build();
        }
        return Response.ok("Contenu supprimé").build();
    }

    /**
     * Récupère le total d'une commande par son identifiant.
     *
     * @param id_commande l'identifiant de la commande
     * @return une réponse contenant le total de la commande
     */
    @GET
    @Path("{id_commande}/total")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommandeTotal(@PathParam("id_commande") int id_commande) {
        double total = service.getCommandeTotal(id_commande);
        return Response.ok(total).build();
    }

    /**
     * Récupère le prix d'une commande par son identifiant.
     *
     * @param id l'identifiant de la commande
     * @return une réponse contenant le prix de la commande
     */
    @GET
    @Path("{id}/prix")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCommandePrix(@PathParam("id") int id) {
        double prix = service.getCommandeTotal(id);
        return Response.ok(prix).build();
    }

    /**
     * Endpoint permettant d'enregistrer une commande
     * 
     * @param id identifiant de la commande
     * @param commande objet Commandes contenant les détails de la commande
     * @return une réponse indiquant le résultat de l'opération
     */
    @POST
    @Path("{id}/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerCommande(@PathParam("id") int id, Commandes commande) {
        // Cette méthode était déjà présente dans le code original mais a été adaptée
        // pour retourner un objet Response au lieu d'une chaîne
        if (service.registerCommande(id, commande)) {
            return Response.ok("Commande enregistrée").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Impossible d'enregistrer la commande").build();
        }
    }
}

