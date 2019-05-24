package Restful;

import entities.Inventory;
import java.sql.SQLException;
import java.util.ArrayList;
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

@Path("inventory")
public class InventoryREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of inventory
     */
    public InventoryREST() {
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Inventory> getJsonAll() throws SQLException {
        InventoryDAO db = new InventoryDAO();
        ArrayList<Inventory> invs = db.getAllInventory();
        return invs;
    } 
    
    @GET
    @Path("allbyemail/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Inventory> getJsonByEmail(@PathParam("email") String email) throws SQLException {

        InventoryDAO db = new InventoryDAO();
        ArrayList<Inventory> invs = db.getAllInventoryByEmail(email);

        return invs;
    }

    @GET
    @Path("byid/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Inventory getJsonById(@PathParam("id") int id) throws SQLException {

        InventoryDAO db = new InventoryDAO();
        Inventory inv = db.findRecordById(id);

        return inv;
    }
    
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInventory(Inventory inventory) throws SQLException {        
        String output = "Failed to create a record";        
        InventoryDAO db = new InventoryDAO();
        int result =db.createInventory(inventory);
        if (result!=-1) {
            output = inventory.toString();
        }        
        return Response.status(200).entity(output).build();
    }
    
    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInventory(Inventory inventory) throws SQLException {        
        String output = "Failed to update a record";        
        InventoryDAO db = new InventoryDAO();
        int result = db.updateInventory(inventory);
        if (result!=-1) {
            output = inventory.toString();
        }        
        return Response.status(200).entity(output).build();
    }
    
    @POST
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInventory(Inventory inventory) throws SQLException {        
        String output = "Failed to update a record";        
        InventoryDAO db = new InventoryDAO();
        int result = db.deleteInventory(inventory.getId());
        if (result!=-1) {
            output = inventory.toString();
        }        
        return Response.status(200).entity(output).build();
    }

    /**
     * PUT method for updating or creating an instance of InventoryREST
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(InventoryREST content) {
    }

}
