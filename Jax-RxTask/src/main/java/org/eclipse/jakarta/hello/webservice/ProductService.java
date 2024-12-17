package org.eclipse.jakarta.hello.webservice;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jakarta.hello.model.Product;
import org.eclipse.jakarta.hello.repository.ProductsRepository;

import java.util.List;

@Path("products")
public class ProductService {

    private final ProductsRepository repository = ProductsRepository.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return repository.getProducts();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addProduct(@FormParam("name") String name, @FormParam("price") double price) {
        Product product = new Product(name, price);
        try {
            repository.addProduct(product);
            return Response.status(Response.Status.CREATED).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateProduct(@FormParam("name") String name, @FormParam("price") double price) {
        Product product = new Product(name, price);
        try {
            repository.updateProduct(product);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response deleteProduct(@FormParam("name") String name) {
        try {
            repository.deleteProduct(name);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }


}
