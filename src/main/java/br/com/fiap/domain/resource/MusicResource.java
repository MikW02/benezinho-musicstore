package br.com.fiap.domain.resource;


import br.com.fiap.domain.DTO.MusicDTO;
import br.com.fiap.domain.entity.Artist;
import br.com.fiap.domain.entity.Music;
import br.com.fiap.domain.service.ArtistService;
import br.com.fiap.domain.service.MusicService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@Path("music/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MusicResource implements Resource<MusicDTO, Long> {

    @Context
    UriInfo uriInfo;

    MusicService service = new MusicService();

    @GET
    @Override
    public Response findAll() {
        List<Music> all = service.findAll();
        return Response.ok(  all.stream().map( MusicDTO::of ).toList() ).build();
    }


    @GET
    @Path("/{id}")
    @Override
    public Response findById(@PathParam("id") Long id) {

        Music music = service.findById( id );

        if (Objects.isNull( music )) return Response.status( 404 ).build();

        return Response.ok( MusicDTO.of( music ) ).build();
    }

    @POST
    @Override
    public Response persist(MusicDTO music) {

        Music persisted = service.persist( MusicDTO.of( music ) );

        if (Objects.isNull( persisted )) return Response.status( 400 ).build();

        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path( String.valueOf( persisted.getId() ) ).build();

        return Response.created( uri ).entity( MusicDTO.of( persisted )).build();

    }


}

