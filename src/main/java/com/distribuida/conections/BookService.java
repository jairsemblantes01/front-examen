package com.distribuida.conections;

import com.distribuida.db.Book;
import jakarta.json.bind.annotation.JsonbAnnotation;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BookService {
@GET
@JsonbAnnotation
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
List<Book> findAll();


@GET
@Path("/{id}")
Book getOne(@PathParam("id") Integer id);


@POST
Book addOne(Book book);


@PUT
@Path("/{id}")
Boolean editOne(@PathParam("id") Integer id, Book book);


@DELETE
@Path("/{id}")
Boolean deleteOne(@PathParam("id") Integer id);

}
