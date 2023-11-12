package br.com.fiap.domain.repository;


import br.com.fiap.domain.entity.Artist;
import br.com.fiap.domain.entity.Music;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MusicRepository implements Repository<Music, Long> {

    private ArtistRepository artistRepository = ArtistRepository.build();
    private ConnectionFactory factory;

    private static final AtomicReference<MusicRepository> instance = new AtomicReference<>();

    private MusicRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static MusicRepository build() {
        instance.compareAndSet( null, new MusicRepository() );
        return instance.get();
    }

    @Override
    public List<Music> findAll() {

        List<Music> musics = new ArrayList<>();

        var sql = """
                SELECT * FROM
                TB_MUSIC
                """;

        Connection conn = factory.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {

            st = conn.createStatement();
            rs = st.executeQuery( sql );

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong( "ID_MUSIC" );
                    String title = rs.getString( "NM_TITLE" );
                    var id_art = rs.getLong("ARTIST");
                    var idArtista = artistRepository.findById(id_art);
                    String style = rs.getString("STYLE");
                    String duration = rs.getString("DURATION");
                    String originalLanguage = rs.getString("ORIGINAL_LANGUAGE");
                    var explicitLyrics = rs.getBoolean( "EXPLICIT_LIRICS" );
                    musics.add( new Music( id, title,idArtista,style,duration,originalLanguage,explicitLyrics ));
                }
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível realizar a consulta ao banco de dados: " + e.getMessage() );
        } finally {
            fecharObjetos( rs, st, conn );
        }

        return musics;
    }



    @Override
    public Music findById(Long id) {

        Music music = null;

        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        var sql = "SELECT * FROM TB_MUSIC WHERE ID_MUSIC = ?";
        try {
            st = con.createStatement();
            rs = st.executeQuery( sql );

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long iddd = rs.getLong( "ID_MUSIC" );
                    String title = rs.getString( "NM_TITLE" );
                    var id_art = rs.getLong("ARTIST");
                    var idArtista = artistRepository.findById(id_art);
                    String style = rs.getString("STYLE");
                    String duration = rs.getString("DURATION");
                    String originalLanguage = rs.getString("ORIGINAL_LANGUAGE");
                    var explicitLyrics = rs.getBoolean( "EXPLICIT_LIRICS" );
                    new Music( iddd, title,idArtista,style,duration,originalLanguage,explicitLyrics );


                }
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, st, con );
        }
        return music;
    }

    @Override
    public Music persiste(Music music) {
        var sql = "INSERT INTO TB_MUSIC (ID_MUSIC, NM_TITLE, ARTIST,STYLE,DURATION,ORIGINAL_LANGUAGE,EXPLICIT_LIRICS) VALUES (0, ?, ?,?,?,?,?)";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString( 1, music.getTitle() );
            ps.setLong( 2, music.getArtist().getId() );
            ps.setString( 3, music.getDuration() );
            ps.setString( 4, music.getOriginalLanguage() );
            ps.setBoolean( 5, music.isExplicitLyrics() );
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                music.setId(id);
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return music;
    }




    @Override
    public Music findByName(String texto) {

        Music music = null;

        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        var sql = "SELECT * FROM TB_MUSIC WHERE ID_MUSIC = ?";
        try {
            ps = con.prepareStatement( sql );
            ps.setString( 1, texto.toUpperCase().trim() );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long iddd = rs.getLong( "ID_MUSIC" );
                    String title = rs.getString( "NM_TITLE" );
                    var id_art = rs.getLong("ARTIST");
                    var idArtista = artistRepository.findById(id_art);
                    String style = rs.getString("STYLE");
                    String duration = rs.getString("DURATION");
                    String originalLanguage = rs.getString("ORIGINAL_LANGUAGE");
                    var explicitLyrics = rs.getBoolean( "EXPLICIT_LIRICS" );
                    new Music( iddd, title,idArtista,style,duration,originalLanguage,explicitLyrics );


                }
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return music;
    }







}