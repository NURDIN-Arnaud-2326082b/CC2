package fr.univamu.iut.product;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


/**
 * Ressource associée aux livres
 * (point d'accès de l'API REST)
 */
@Path("/productsandusers")
@ApplicationScoped
public class ProductResource {

    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    private ProductService service;

    private UserService service2;

    /**
     * Constructeur par défaut
     */
    public ProductResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param productRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject ProductResource(ProductRepositoryInterface productRepo, UserRepositoryInterface userRepo ) {
        this.service = new ProductService( productRepo) ;
        this.service2 = new UserService(userRepo);
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
        return service.getAllProductsJSON();
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

        String result = service.getProductJSON(reference);

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
        if( ! service.updateProduct(reference, product) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
        /**
         * Enpoint permettant de publier de tous les livres enregistrés
         * @return la liste des livres (avec leurs informations) au format JSON
         */
        @GET
        @Produces("application/json")
        public String getAllUsers() {
            return service2.getAllUsersJSON();
        }

        /**
         * Endpoint permettant de publier les informations d'un livre dont la référence est passée paramètre dans le chemin
         * @param id référence du livre recherché
         * @return les informations du livre recherché au format JSON
         */
        @GET
        @Path("{id}")
        @Produces("application/json")
        public String getUser(@PathParam("id") int id){

            String result = service2.getUserJSON(id);

            // si le livre n'a pas été trouvé
            if( result == null )
                throw new NotFoundException();

            return result;
        }

        /**
         * Endpoint permettant de mettre à jours le statut d'un livre uniquement
         * (la requête patch doit fournir le nouveau statut sur livre, les autres informations sont ignorées)
         * @param id la référence du livre dont il faut changer le statut
         * @param user le livre transmis en HTTP au format JSON et convertit en objet Book
         * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
         */
        @PUT
        @Path("{id}")
        @Consumes("application/json")
        public Response updateUser(@PathParam("id") int id, User user){

            // si le livre n'a pas été trouvé
            if( ! service2.updateUser(id, user) )
                throw new NotFoundException();
            else
                return Response.ok("updated").build();
        }
}
