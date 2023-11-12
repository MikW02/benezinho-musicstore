package br.com.fiap.domain.service;


import br.com.fiap.domain.entity.Artist;
import br.com.fiap.domain.entity.Music;
import br.com.fiap.domain.repository.ArtistRepository;
import br.com.fiap.domain.repository.MusicRepository;

import java.util.List;
import java.util.Objects;

public class MusicService implements Service<Music, Long> {



    MusicRepository repo = MusicRepository.build();

    @Override
    public List<Music> findAll() {
        return repo.findAll();
    }

    @Override
    public Music findById(Long id) {
        return repo.findById( id );
    }


    @Override
    public Music persist(Music music) {

        //pra não ter 2 com o mesmo nome

        var m = repo.findByName( music.getTitle() );
        if (Objects.nonNull( m )) {
            System.err.println( "Já existe musica cadastrado com o mesmo Título: " + m.getTitle() );
            return m;
        }
        return repo.persiste( music );
    }


}

