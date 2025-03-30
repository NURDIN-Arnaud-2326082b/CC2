package fr.univamu.iut.commandes;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


/**
 * Ressource associée aux livres
 * (point d'accès de l'API REST)
 */
@Path("/commandes")
@ApplicationScoped
public class CommandesResource {


    /**
     * Service utilisé pour accéder aux données des livres et récupérer/modifier leurs informations
     */
    private CommandesService service;

    /**
     * Constructeur par défaut
     */
    public CommandesResource(){}

    /**
     * Constructeur permettant d'initialiser le service avec une interface d'accès aux données
     * @param CommandesRepo objet implémentant l'interface d'accès aux données
     */
    public @Inject CommandesResource(CommandesRepositoryInterface CommandesRepo ){
        this.service = new CommandesService( CommandesRepo) ;
    }

    /**
     * Constructeur permettant d'initialiser le service d'accès aux livres
     */
    public CommandesResource(CommandesService service ){
        this.service = service;
    }

    /**
     * Enpoint permettant de publier de tous les livres enregistrés
     * @return la liste des livres (avec leurs informations) au format JSON
     */
    @GET
    @Produces("application/json")
    public String getAllCommandess() {
        return service.getAllCommandessJSON();
    }

    /**
     * Endpoint permettant de publier les informations d'un livre dont la référence est passée paramètre dans le chemin
     * @param reference référence du livre recherché
     * @return les informations du livre recherché au format JSON
     */
    @GET
    @Path("{reference}")
    @Produces("application/json")
    public String getCommandes( @PathParam("reference") String reference){

        String result = service.getCommandesJSON(reference);

        // si le livre n'a pas été trouvé
        if( result == null )
            throw new NotFoundException();

        return result;
    }

    /**
     * Endpoint permettant de mettre à jours le statut d'un livre uniquement
     * (la requête patch doit fournir le nouveau statut sur livre, les autres informations sont ignorées)
     * @param reference la référence du livre dont il faut changer le statut
     * @param Commandes le livre transmis en HTTP au format JSON et convertit en objet Commandes
     * @return une réponse "updated" si la mise à jour a été effectuée, une erreur NotFound sinon
     */
    @PUT
    @Path("{reference}")
    @Consumes("application/json")
    public Response updateCommandes(@PathParam("reference") String reference, Commandes Commandes ){

        // si le livre n'a pas été trouvé
        if( ! service.updateCommandes(reference, Commandes) )
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }
}
