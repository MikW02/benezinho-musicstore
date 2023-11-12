package br.com.fiap.domain.entity;


public class Music {
    private Long id;
    private String title;
    private Artist artist;
    private String style;
    private String duration;
    private String originalLanguage;
    private boolean explicitLyrics;


    public Music(Long id, String title, Artist artist, String style, String duration, String originalLanguage, boolean explicitLyrics) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.style = style;
        this.duration = duration;
        this.originalLanguage = originalLanguage;
        this.explicitLyrics = explicitLyrics;
    }


    public Music() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public boolean isExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", style='" + style + '\'' +
                ", duration='" + duration + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", explicitLyrics=" + explicitLyrics +
                '}';
    }
}