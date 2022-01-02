package fact.it.servicesong.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "songs")
public class Song {
    @Id
    private String id;
    private String ISRC;
    private String MBID;
    private String genre;
    private String title;
    private Integer length;

    public Song() {
    }

    public Song(String ISRC, String MBID,String genre, String title, Integer length) {
        setISRC(ISRC);
        setMBID(MBID);
        setGenre(genre);
        setTitle(title);
        setLength(length);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getISRC() {
        return ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public String getMBID() { return MBID; }

    public void setMBID(String MBID) { this.MBID = MBID; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
