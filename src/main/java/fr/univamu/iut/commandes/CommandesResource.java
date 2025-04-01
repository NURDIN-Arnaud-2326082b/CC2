package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/commandes")
@ApplicationScoped
public class CommandesResource {

    private CommandesService service;

    public CommandesResource() {}

    @Inject
    public CommandesResource(@MariaDB CommandesRepositoryInterface commandesRepo) {
        this.service = new CommandesService(commandesRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllCommandes() {
        return service.getAllCommandesJSON();
    }

    @GET
    @Path("{id_commande}")
    @Produces("application/json")
    public String getCommande(@PathParam("id_commande") int id_commande) {
        String result = service.getCommandeJSON(id_commande);
        if (result == null) throw new NotFoundException();
        return result;
    }

    @PUT
    @Path("{id_commande}")
    @Consumes("application/json")
    public Response updateCommande(@PathParam("id_commande") int id_commande, Commandes commande) {
        if (!service.updateCommande(id_commande, commande))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    @POST
    @Consumes("application/json")
    public Response createCommande(Commandes commande) {
        if (service.createCommande(commande))
            return Response.ok("created").build();
        else
            return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id_commande}")
    public Response deleteCommande(@PathParam("id_commande") int id_commande) {
        if (service.deleteCommande(id_commande))
            return Response.ok("deleted").build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
