package br.com.fiap.domain.DTO;


import br.com.fiap.domain.entity.Artist;

import br.com.fiap.domain.service.ArtistService;


import java.util.Objects;

public record ArtistDTO(Long id, String name) {

    private static ArtistService service = new ArtistService();

    public static Artist of(ArtistDTO dto) {
        // É nulo?
        if (Objects.isNull( dto )) return null;

        //Ele informou o id do produto?
        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        //Se não informou o Id é porque está salvando um novo artist
        Artist a = new Artist();
        a.setId( null );
        a.setName( dto.name );

        return a;
    }


    public static ArtistDTO of(Artist a) {
        return new ArtistDTO( a.getId(), a.getName() );
    }

}
