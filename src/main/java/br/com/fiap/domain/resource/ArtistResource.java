package br.com.fiap.domain.resource;


import br.com.fiap.domain.DTO.ArtistDTO;
import br.com.fiap.domain.entity.Artist;
import br.com.fiap.domain.service.ArtistService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("/artist")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtistResource implements Resource<ArtistDTO, Long> {

    @Context
    UriInfo uriInfo;

    ArtistService service = new ArtistService();

    @GET
    @Override
    public Response findAll() {
        List<Artist> all = service.findAll();
        return Response.ok( all.stream().map( ArtistDTO::of ).toList() ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Artist artist = service.findById( id );

        if (Objects.isNull( artist )) return Response.status( 404 ).build();

        return Response.ok( ArtistDTO.of( artist)  ).build();
    }

    @POST
    @Override
    public Response persist(ArtistDTO artist) {

        Artist persisted = service.persist( ArtistDTO.of( artist));

        if (Objects.isNull(persisted)) return Response.status(400).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( persisted.getId() ) ).build();

        return Response.created( uri ).entity( ArtistDTO.of(persisted) ).build();

    }


}

