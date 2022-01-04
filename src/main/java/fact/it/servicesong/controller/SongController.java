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

            songRepository.save(new Song("GBAHT2000193","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Trouble’s Coming",228,"6voIJ7OWwRabSZDC77D5Hp"));
            songRepository.save(new Song("GBAHT2001120","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Oblivion",161,"3Ye5icBka8ODjcaEQakPvZ"));
            songRepository.save(new Song("GBAHT2001121","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Typhoons",236,"5aFGo8wHEntVxFI8IF7Wuj"));
            songRepository.save(new Song("GBAHT2001122","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Who Needs Friends",190,"7AXoSHtReIvoJPi5XKXecl"));
            songRepository.save(new Song("GBAHT2001123","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Million and One",258,"7AXoSHtReIvoJPi5XKXecl"));
            songRepository.save(new Song("GBAHT2001124","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Limbo",293,"1P8BrsNLHWO5R0cK6zvyhc"));
            songRepository.save(new Song("GBAHT2001125","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Either You Want It",180,"1P8BrsNLHWO5R0cK6zvyhc"));
            songRepository.save(new Song("GBAHT2001126","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Boilermaker",209,"27BEATf1JFhKDmwJdpGVSk"));
            songRepository.save(new Song("GBAHT2001127","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Mad Visions",189,"3S66ufJ1RdjOKf2azjXWjI"));
            songRepository.save(new Song("GBAHT2001128","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","Hold On",194,"5rUGbardlhPNzbHH3qOEOk"));
            songRepository.save(new Song("GBAHT2001129","aa62b28e-b6d4-4086-91d4-e5fac1ed56f3","dd7e7ced-a44d-4ce5-9654-c60a0d71fc51","Rock","All We Have Is Now",213,"4CUyNgMxAFKFEf1KrbAEbY"));


        };
    }




}
