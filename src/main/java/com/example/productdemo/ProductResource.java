package com.example.productdemo;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.example.productdemo.model.Product;

@Path("products")
@Produces("text/xml")
public class ProductResource {
    
    private Map<Integer, Product> products = new HashMap<>() {{
        put(1, new Product(1, "Hello World"));
    }};
    
    @GET
    @Path("{productId}")
    public Product getProduct(@PathParam("productId") int productId) {
        return products.get(productId);
    }
    
    @POST
    public Response createProduct(Product product) {
        if(products.containsKey(product.getId())) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        products.put(product.getId(), product);
        return Response.ok(product).build();
    }
}
