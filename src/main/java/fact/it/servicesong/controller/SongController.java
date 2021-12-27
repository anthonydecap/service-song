package fact.it.servicesong.controller;

import fact.it.servicesong.model.Song;
import fact.it.servicesong.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    private SongRepository songRepository;

    @GetMapping("/songs/album/{albumId}")
    public List<Song> getSongsByAlbumId(@PathVariable String albumId) {
        return songRepository.findSongsByAlbumId(albumId);
    }

    @GetMapping("/songs/{ISRC}")
    public List<Song> getSongsByISRC(@PathVariable String ISRC) {
        return songRepository.findSongsByISRC(ISRC);
    }

    @GetMapping("/reviews/album/{albumId}/{ISRC}")
    public Song getSongsByISRC(@PathVariable String albumId, @PathVariable String ISRC) {
        return songRepository.findSongByAlbumIdAndISRC(albumId, ISRC);
    }

    @PostMapping("/songs")
    public Song addReview(@RequestBody Song song) {
        songRepository.save(song);
        return song;
    }

    @PutMapping("/songs")
    public Song updateSong(@RequestBody Song updateSong) {
        Song retrievedSong = songRepository.findSongByAlbumIdAndISRC(
            updateSong.getAlbumId(), updateSong.getISRC());

        retrievedSong.setISRC(updateSong.getISRC());
        retrievedSong.setTitle(updateSong.getTitle());
        retrievedSong.setLength(updateSong.getLength());
        retrievedSong.setTitle(updateSong.getTitle());

        songRepository.save(retrievedSong);

        return retrievedSong;
    }

    @DeleteMapping("/reviews/album/{albumId}/{ISRC}")
    public ResponseEntity deleteSong(@PathVariable String albumId, @PathVariable String ISRC) {
        Song song = songRepository.findSongByAlbumIdAndISRC(albumId, ISRC);
        if(song!=null) {
            songRepository.delete(song);
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }




}
