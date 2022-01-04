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

            songRepository.save(new Song("GBAYE9400059","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Planet Telex",259,"37JISltgxizbDAyNEEqkTY"));
            songRepository.save(new Song("GBAYE9400054","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","The Bends",246,"7oDFvnqXkXuiZa1sACXobj"));
            songRepository.save(new Song("GBAYE9400055","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","High and Dry",257,"2a1iMaoWQ5MnvLFBDv4qkf"));
            songRepository.save(new Song("GBAYE9400056","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Fake Plastic Trees",290,"73CKjW3vsUXRpy3NnX4H7F"));
            songRepository.save(new Song("GBAYE9400057","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Bones",189,"76RAlQcfuQknnQFruYDj6Q"));
            songRepository.save(new Song("GBAYE9400058","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","(Nice Dream)",233,"1tZcw7GtIqviL32bzaKdSo"));
            songRepository.save(new Song("GBAYE9400060","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Just",234,"1dyTcli07c77mtQK3ahUZR"));
            songRepository.save(new Song("GBAYE9400065","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","My Iron Lung",276,"0jyikFM0Umv0KlnrOEKtTG"));
            songRepository.save(new Song("GBAYE9400064","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Bullet Proof ... I Wish I Was",208,"5XuU9htN358NTMCcqRvfDV"));
            songRepository.save(new Song("GBAYE9400063","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Black Star",247,"6UO72VSXEONxdfLyABihs9"));
            songRepository.save(new Song("GBAYE9400062","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Sulk",222,"1elQc2QcyuBkI8FUIbNvcy"));
            songRepository.save(new Song("GBAYE9400061","a74b1b7f-71a5-4011-9441-d0b5e4122711","b8048f24-c026-3398-b23a-b5e50716cbc7","Rock","Street Spirit (Fade Out)",253,"2QwObYJWyJTiozvs0RI7CF"));
        };
    }




}
