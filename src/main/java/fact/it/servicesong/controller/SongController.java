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

    @GetMapping("/songs/{title}")
    public List<Song> getSongsByTitle(@PathVariable String title) {
        return songRepository.findSongsByTitle(title);
    }

    @GetMapping("/songs/{ISRC}")
    public List<Song> getSongsByISRC(@PathVariable String ISRC) {
        return songRepository.findSongsByISRC(ISRC);
    }

    @GetMapping("/songs/album/{ISRC}/{title}/")
    public Song getSongsByISRC(@PathVariable String ISRC, @PathVariable String title) {
        return songRepository.findSongByISRCAndTitle(ISRC,title);
    }

    @PostMapping("/songs")
    public Song addReview(@RequestBody Song song) {
        songRepository.save(song);
        return song;
    }

    @PutMapping("/songs")
    public Song updateSong(@RequestBody Song updateSong) {
        Song retrievedSong = songRepository.findSongByISRCAndTitle(
            updateSong.getISRC(), updateSong.getTitle());

        retrievedSong.setISRC(updateSong.getISRC());
        retrievedSong.setTitle(updateSong.getTitle());
        retrievedSong.setLength(updateSong.getLength());

        songRepository.save(retrievedSong);

        return retrievedSong;
    }

    @DeleteMapping("/songs/album/{ISRC}/{title}/")
    public ResponseEntity deleteSong(@PathVariable String ISRC, @PathVariable String title) {
        Song song = songRepository.findSongByISRCAndTitle(ISRC,title);
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
            songRepository.save(new Song("7875455454","Roxanne",120));
            songRepository.save(new Song("7875455454","De DO DO",129));
            songRepository.save(new Song("8452131444","Goosebumps",284));
            songRepository.save(new Song("8452131444","Highest in the Room",180));
        }

        System.out.println("Songs test:" + songRepository.findSongsByISRC("7875455454").size());
    }




}
