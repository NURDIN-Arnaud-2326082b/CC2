package fr.univamu.iut.CC2;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/Panier")
@ApplicationScoped
public class PanierResource {

    private PanierService service;

    public PanierResource() {}

    @Inject
    public PanierResource(PanierRepositoryInterface bookRepo) {
        this.service = new PanierService(bookRepo);
    }

    public PanierResource(PanierService service) {
        this.service = service;
    }

    @GET
    @Produces("application/json")
    public String getAllPanier() {
        return service.getAllPanierJSON();
    }

    @GET
    @Path("{IdPanier}")
    @Produces("application/json")
    public String getPanier(@PathParam("IdPanier") int IdPanier) {
        String result = service.getPanierJSON(IdPanier);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("{IdPanier}")
    @Consumes("application/json")
    public Response updateBook(@PathParam("IdPanier") int IdPanier, Panier panier) {
        if (!service.updatePanier(IdPanier, panier)) {
            throw new NotFoundException();
        } else {
            return Response.ok("updated").build();
        }
    }
}