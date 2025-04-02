package fr.univamu.iut.productsandusers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

/**
 * Ressource associée aux products
 * (point d'accès de l'API REST)
 */
@Path("/users")
@ApplicationScoped
public class UserResource {

    /**
     * Service utilisé pour accéder aux données des products et récupérer/modifier leurs informations
     */
    private UserService service;

    /**
     * Constructeur par défaut
     */
    public UserResource() {}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param userRepo objet implémentant l'interface d'accès aux données
     */
    @Inject
    public UserResource(UserRepositoryInterface userRepo) {
        this.service = new UserService(userRepo);
    }

    /**
     * Enpoint permettant de publier de tous les produits enregistrés
     * @return la liste des produits (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un produit dont la référence est passée paramètre dans le chemin
     * @param id référence du produit recherché
     * @return les informations du produit recherché au format JSON
     */
    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        String result = service.getUserJSON(id);
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    /**
     * Endpoint permettant de mettre à jours le statut d'un produit uniquement
     * @param id la référence du produit dont il faut changer le statut
     * @param user le produit transmis en HTTP au format JSON et convertit en objet Product
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateUser(@PathParam("id") int id, User user) {
        if (!service.updateUser(id, user))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    /**
     * Endpoint permettant de créer un utilisateur
     * @param user l'utilisateur à créer
     * @return une réponse "created" si l'utilisateur a été créé
     */
    @POST
    @Consumes("application/json")
    public Response createUser(User user) {
        service.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     * Endpoint permettant de supprimer un utilisateur
     * @param id la référence de l'utilisateur à supprimer
     * @return une réponse "no content" si l'utilisateur a été supprimé
     */
    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        if (!service.deleteUser(id))
            throw new NotFoundException();
        else
            return Response.noContent().build();
    }
}
