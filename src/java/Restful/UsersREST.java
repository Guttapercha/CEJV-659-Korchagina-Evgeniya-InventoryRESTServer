package Restful;

import entities.Credentials;
import entities.Inventory;
import entities.Users;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Evgeniya
 */
@Path("users")
public class UsersREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsersREST
     */
    public UsersREST() {
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Users> getJsonAll() throws SQLException {
        UsersDAO db = new UsersDAO();
        ArrayList<Users> users = db.getAllUsers();
        return users;
    } 
    
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response passwordValidator(Credentials credentials) throws SQLException {
        String output = "Failed! Wrong credentials!";
        UsersDAO db = new UsersDAO();
        boolean result = db.passwordValidation(credentials.getEmail(), credentials.getPassword());
        if (result) {
            output = "SUCCESS! Yuo are logged in!";
        }
        return Response.status(200).entity(output).build();
    }
    
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(Users user) throws SQLException {        
        String output = "Failed to create a record";        
        UsersDAO db = new UsersDAO();
        int result =db.createUser(user);
        if (result!=-1) {
            output = user.toString();
        }        
        return Response.status(200).entity(output).build();
    }

    /**
     * PUT method for updating or creating an instance of UsersREST
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(Users content) {
    }
}
