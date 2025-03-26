package fr.univamu.iut.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


/**
 * Ressource associée aux livres
 * (point d'accès de l'API REST)
 */
@Path("/products")
@ApplicationScoped
public class ProductResource {

    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    private ProductService service;

    /**
     * Constructeur par défaut
     */
    public ProductResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param bookRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject ProductResource(ProductRepositoryInterface bookRepo ){
        this.service = new ProductService( bookRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux livres
     */
    public ProductResource(ProductService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les livres enregistrés
     * @return la liste des livres (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllBooksJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un livre dont la référence est passée paramètre dans le chemin
     * @param reference référence du livre recherché
     * @return les informations du livre recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getProduct(@PathParam("reference") String reference){

        String result = service.getBookJSON(reference);

        // si le livre n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jours le statut d'un livre uniquement
     * (la requête patch doit fournir le nouveau statut sur livre, les autres informations sont ignorées)
     * @param reference la référence du livre dont il faut changer le statut
     * @param product le livre transmis en HTTP au format JSON et convertit en objet Book
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updateProduct(@PathParam("reference") String reference, Product product){

        // si le livre n'a pas été trouvé
        if( ! service.updateBook(reference, product) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
}
