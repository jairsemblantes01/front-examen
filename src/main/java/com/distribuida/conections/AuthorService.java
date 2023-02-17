package com.distribuida.conections;

import com.distribuida.db.Author;
import com.distribuida.db.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/authors")
@Produces({ MediaType.APPLICATION_JSON })
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthorService {
	@GET
	List<Author> findAll();
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	Author getOne(@PathParam("id") Integer id);
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	Author addOne(Author author);
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	Boolean editOne(@PathParam("id") Integer id, Author author);
	
	@DELETE
	@Path("/{id}")
	Boolean deleteOne(@PathParam("id") Integer id);
	
}
