package fr.univamu.iut.productsandusers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/users")
@ApplicationScoped
public class UserResource {

    private UserService service;

    public UserResource() {}

    @Inject
    public UserResource(UserRepositoryInterface userRepo) {
        this.service = new UserService(userRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        String result = service.getUserJSON(id);
        if (result == null)
            throw new NotFoundException();
        return result;
    }

    @PUT
    @Path("{id}")
    @Consumes("application/json")
    public Response updateUser(@PathParam("id") int id, User user) {
        if (!service.updateUser(id, user))
            throw new NotFoundException();
        else
            return Response.ok("updated").build();
    }

    @POST
    @Consumes("application/json")
    public Response createUser(User user) {
        service.createUser(user);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteUser(@PathParam("id") int id) {
        if (!service.deleteUser(id))
            throw new NotFoundException();
        else
            return Response.noContent().build();
    }
}
