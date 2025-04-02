package fr.univamu.iut.productsandusers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


/**
 * Ressource associée aux products
 * (point d'accès de l'API REST)
 */
@Path("/products")
@ApplicationScoped
public class ProductResource {

    /**
     * Service utilisé pour accéder aux données des products et récupérer/modifier leurs informations
     */
    private ProductService service;

    /**
     * Constructeur par défaut
     */
    public ProductResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param productRepo objet implémentant l'interface d'accès aux données
     */
    @Inject
    public ProductResource(ProductRepositoryInterface productRepo) {
        this.service = new ProductService( productRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux produits
     */
    public ProductResource(ProductService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les produits enregistrés
     * @return la liste des produits (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllProducts() {
        return service.getAllProductsJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un produit dont la référence est passée paramètre dans le chemin
     * @param reference référence du produit recherché
     * @return les informations du produit recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getProduct(@PathParam("reference") String reference){

        String result = service.getProductJSON(reference);

        // si le livre n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jours le statut d'un produit uniquement
     * @param reference la référence du produit dont il faut changer le statut
     * @param product le produit transmis en HTTP au format JSON et convertit en objet Product
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updateProduct(@PathParam("reference") String reference, Product product){

        // si le livre n'a pas été trouvé
        if( ! service.updateProduct(reference, product))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant de créer un nouveau produit
     * @param product le produit à créer
     * @return une réponse "created" si le produit a été créé, une erreur sinon
     */
    @POST
    @Consumes("application/json")
    public Response createProduct(Product product) {
        if (service.createProduct(product))
            return Response.status(Response.Status.CREATED).entity("created").build();
        else
            return Response.status(Response.Status.BAD_REQUEST).entity("error").build();
    }

    /**
     * Endpoint permettant de supprimer un produit
     * @param reference la référence du produit à supprimer
     * @return une réponse "deleted" si le produit a été supprimé, une erreur NotFound sinon
     */
    @DELETE
    @Path("{reference}")
    public Response deleteProduct(@PathParam("reference") String reference) {
        if (service.deleteProduct(reference))
            return Response.ok("deleted").build();
        else
            throw new NotFoundException();
    }
}
