package br.com.fiap.domain.DTO;

import br.com.fiap.domain.entity.Artist;
import br.com.fiap.domain.entity.Music;
import br.com.fiap.domain.service.MusicService;

import java.util.Objects;

public record MusicDTO( Long id,
         String title,
        ArtistDTO artist,
         String style,
         String duration,
         String originalLanguage,
         boolean explicitLyrics) {

    private static MusicService service = new MusicService();

    public static Music of(MusicDTO dto) {

        if (Objects.isNull( dto )) return null;

        //Informou id do produto?
        if (Objects.nonNull( dto.id )) return service.findById( dto.id );

        //Se não informou o Id é porque está salvando um novo Music
        Music m = new Music();
        m.setId( null );
        m.setTitle( dto.title );

        return m;
    }


    public static MusicDTO of(Music m) {
        MusicDTO dto =  new MusicDTO( m.getId(), m.getTitle(),ArtistDTO.of(m.getArtist()),m.getStyle(), m.getDuration(), m.getOriginalLanguage(), m.isExplicitLyrics());
        return dto;
    }

}
