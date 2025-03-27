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
    public String getAllBooks() {
        return service.getAllBooksJSON();
    }

    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getBook(@PathParam("reference") String reference) {
        String result = service.getBookJSON(reference);
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updateBook(@PathParam("reference") String reference, Panier panier) {
        if (!service.updateBook(reference, panier)) {
            throw new NotFoundException();
        } else {
            return Response.ok("updated").build();
        }
    }
}