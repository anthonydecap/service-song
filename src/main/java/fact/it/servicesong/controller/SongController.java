package fact.it.servicesong.controller;

import fact.it.servicesong.model.Song;
import fact.it.servicesong.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/songs/{ISRC}")
    public Song getSongByISRC(@PathVariable String ISRC) {
        return songRepository.findSongByISRC(ISRC);
    }

    @GetMapping("/songs/artist/{MBID}")
    public List<Song> getSongsByMBID(@PathVariable String MBID) { return songRepository.findSongsByMBID(MBID); }

    @GetMapping("/songs/album/{MAID}")
    public List<Song> getSongsByMAID(@PathVariable String MAID) { return songRepository.findSongsByMAID(MAID); }

    @GetMapping("/songs/genre/{genre}")
    public List<Song> getSongsByGenre(@PathVariable String genre) {
        return songRepository.findSongsByGenre(genre);
    }

    @PostMapping("/songs")
    public Song addReview(@RequestBody Song song) {
        songRepository.save(song);
        return song;
    }

    @PutMapping("/songs")
    public Song updateSong(@RequestBody Song updateSong) {
        Song retrievedSong = songRepository.findSongByISRC(
            updateSong.getISRC());

        retrievedSong.setISRC(updateSong.getISRC());
        retrievedSong.setTitle(updateSong.getMBID());
        retrievedSong.setTitle(updateSong.getMAID());
        retrievedSong.setTitle(updateSong.getGenre());
        retrievedSong.setTitle(updateSong.getTitle());
        retrievedSong.setLength(updateSong.getLength());
        retrievedSong.setUrl(updateSong.getUrl());

        songRepository.save(retrievedSong);

        return retrievedSong;
    }

    @DeleteMapping("/songs/{ISRC}")
    public ResponseEntity deleteSong(@PathVariable String ISRC) {
        Song song = songRepository.findSongByISRC(ISRC);
        if(song!=null) {
            songRepository.delete(song);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostConstruct
    public void fillDB() {
        if(songRepository.count()==0) {
            songRepository.save(new Song("ISRC7875455454","MBID1","MAID1","Rock","Roxanne",120,"url"));
            songRepository.save(new Song("ISRC","MBID1","MAID1","Rock","De DO DO",120,"url"));
            songRepository.save(new Song("ISRC6242455454","MBID2","MAID2","rap","Goosbumps",120,"url"));
            songRepository.save(new Song("ISRC6242455455","MBID2","MAID2","rap","Highest in the room",120,"url"));
        }

        System.out.println("Song Service");
        System.out.println("Songs test:" + songRepository.findSongsByMBID("MBID1").size());
    }




}
