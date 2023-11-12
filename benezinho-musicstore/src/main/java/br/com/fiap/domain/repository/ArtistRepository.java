package br.com.fiap.domain.repository;

import br.com.fiap.domain.entity.Artist;
import br.com.fiap.infra.ConnectionFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ArtistRepository implements Repository<Artist, Long> {


    private ConnectionFactory factory;

    private static final AtomicReference<ArtistRepository> instance = new AtomicReference<>();

    private ArtistRepository() {
        this.factory = ConnectionFactory.build();
    }

    public static ArtistRepository build() {
        instance.compareAndSet( null, new ArtistRepository() );
        return instance.get();
    }

    @Override
    public List<Artist> findAll() {
        List<Artist> list = new ArrayList<>();
        Connection con = factory.getConnection();
        ResultSet rs = null;
        Statement st = null;
        var sql = "SELECT * FROM TB_ARTISTA";
        try {
            st = con.createStatement();
            rs = st.executeQuery( sql );

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong( "ID_ARTISTA" );
                    String nome = rs.getString( "NM_ARTISTA" );
                    String nationality = rs.getString( "NACIONALIDADE" );
                    list.add( new Artist( id, nome,nationality ) );
                }
            }
        } catch (SQLException e) {
            System.err.println( "Não foi possível consultar os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( rs, st, con );
        }
        return list;
    }



    @Override
    public Artist findById(Long id) {

        Artist pessoa = null;

        Connection con = factory.getConnection();

        ResultSet rs = null;

        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_ARTISTA where id_artista=?";

        try {
            ps = con.prepareStatement( sql );

            ps.setLong( 1, id );

            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    String nome = rs.getString( "NM_ARTISTA" );
                    String nationality = rs.getString( "NACIONALIDADE" );
                    pessoa = new Artist( id, nome, nationality);
                }
            }
        } catch (SQLException e) {
            System.out.println( "Dados não encontrados com o id: " + id );
        } finally {
            fecharObjetos( rs, ps, con );
        }
        return pessoa;
    }

    @Override
    public Artist persiste(Artist artist) {
        var sql = "INSERT INTO TB_ARTISTA (ID_ARTISTA, NM_ARTISTA, NACIONALIDADE) VALUES (0, ?, ?)";
        Connection con = factory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString( 1, artist.getName() );
            ps.setString( 2, artist.getNationality() );
            ps.executeUpdate();

            final ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                final Long id = rs.getLong(1);
                artist.setId(id);
            }

        } catch (SQLException e) {
            System.err.println( "Não foi possível inserir os dados!\n" + e.getMessage() );
        } finally {
            fecharObjetos( null, ps, con );
        }
        return artist;
    }


    @Override
    public Artist findByName(String texto) {

        Artist pessoa = null;

        Connection conn = factory.getConnection();

        ResultSet rs = null;

        PreparedStatement ps = null;

        var sql = "SELECT * FROM TB_ARTISTA where nm_artista=?";

        try {
            ps = conn.prepareStatement( sql );
            ps.setString( 1, texto.toUpperCase().trim() );
            rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Long id = rs.getLong( "ID_ARTISTA" );
                    String nome = rs.getString( "NM_ARTISTA" );
                    String nationality = rs.getString( "NACIONALIDADE" );
                    pessoa = new Artist( id, nome, nationality);
                }
            }
        } catch (SQLException e) {
            System.out.println( "Dados não encontrados com o id: " + e.getMessage() );
        } finally {
            fecharObjetos( rs, ps, conn );
        }
        return pessoa;
    }









}
