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
        return songRepository.findFirstByISRC(ISRC);
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
        Song retrievedSong = songRepository.findFirstByISRC(
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
        Song song = songRepository.findFirstByISRC(ISRC);
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

            songRepository.save(new Song("GBAYE9200113","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","You",208,"5KZ0qobWEFl892YjIC02SE"));
            songRepository.save(new Song("GBAYE9200070","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Creep",238,"70LcF31zb1H0PyJoS1Sx1r"));
            songRepository.save(new Song("GBAYE9300105","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","How Do You?",132,"5qsgK2wcodYCEbgbdCpYOG"));
            songRepository.save(new Song("GBAYE9300106","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Stop Whispering",325,"3CbAW3GjkBKfErt4LLbSzr"));
            songRepository.save(new Song("GBAYE9200114","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Thinking About You",144,"46tfxn5lP7Qsbz7NHsj9iu"));
            songRepository.save(new Song("GBAYE9300107","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Anyone Can Play Guitar",217,"23oUaizFBFVFI5PxJrkiO5"));
            songRepository.save(new Song("GBAYE9300108","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Ripcord",189,"2wOvYLilnDJfuPXGHGFAWZ"));
            songRepository.save(new Song("GBAYE9300109","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Vegetable",192,"6HbWoyinLdXPZmk6xONuKw"));
            songRepository.save(new Song("GBAYE9200115","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Prove Yourself",145,"0GDuL9TCO41PgsrKWBSGlm"));
            songRepository.save(new Song("GBAYE9300110","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","I Can't",253,"13nQ70PnhDnTkYqCmdg3sy"));
            songRepository.save(new Song("GBAYE9200116","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Lurgee",187,"30C1FoJzEhNUILsxghioGz"));
            songRepository.save(new Song("GBAYE9300111","a74b1b7f-71a5-4011-9441-d0b5e4122711","cd76f76b-ff15-3784-a71d-4da3078a6851","Rock","Blow Out",282,"5XpcTQlNnfIQbiWE4hvYo7"));

            songRepository.save(new Song("GBAAM0201170","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Message In A Bottle",290,"1oYYd2gnWZYrt89EBXdFiO"));
            songRepository.save(new Song("GBAAM0201171","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Reggatta De Blanc",185,"2EEp2vTGSRDSLHWUF80EZZ"));
            songRepository.save(new Song("GBAAM0201172","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","It's Alright For You",192,"5fTI7JCaMRK09WtwG8ZrRK"));
            songRepository.save(new Song("GBAAM0201173","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Bring On The Night",255,"4EkNINvDLeGgIL4zYGsYPb"));
            songRepository.save(new Song("GBAAM0201174","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Deathwish",251,"4i3SC58kB65zfKo1oOW1q9"));
            songRepository.save(new Song("GBAAM0201175","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Walking On The Moon",300,"62uLNJgVZaFiEiKV4LpoYJ"));
            songRepository.save(new Song("GBAAM0201176","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","On Any Other Day",176,"6rN4PWNTD8AY1mfLslqrQG"));
            songRepository.save(new Song("GBAAM0201177","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","The Bed's Too Big Without You",265,"3PfBnnkOf0LbCw2jixUCQG"));
            songRepository.save(new Song("GBAAM0201178","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Contact",157,"5MuKkqc8lnzldouHA0MwgL"));
            songRepository.save(new Song("GBAAM0201179","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","Does Everyone Stare",227,"45BfHifOOhDpyPJn7El1JU"));
            songRepository.save(new Song("GBAAM0201180","9e0e2b01-41db-4008-bd8b-988977d6019a","2b98e6d7-a521-332f-961e-d281ba33ba3d","Rock","No Time This Time",197,"5qolpk9X28wwWLGE8sZ328"));

            songRepository.save(new Song("GBAHT0500591","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Take a Bow",275,"4jrCMOG9OPe6iF4vWFxatb"));
            songRepository.save(new Song("GBAHT0500592","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Starlight",240,"3skn2lauGk7Dx6bVIt5DVj"));
            songRepository.save(new Song("GBAHT0500593","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Supermassive Black Hole",212,"3lPr8ghNDBLc2uZovNyLs9"));
            songRepository.save(new Song("GBAHT0500594","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Map of the Problematique",258,"5YXr4AGfUQpLSxtFSsKUh6"));
            songRepository.save(new Song("GBAHT0500595","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Soldier's Poem",124,"6jH5aCuXgtygWpx7BF54at"));
            songRepository.save(new Song("GBAHT0500596","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Invincible",301,"2zmR3FG7iOGDAdwrVPzdg9"));
            songRepository.save(new Song("GBAHT0500597","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Assassin",211,"6JnFVmPyJvjnfBag0hhIFa"));
            songRepository.save(new Song("GBAHT0500601","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Exo-Politics",233,"20vZII9Yu52czI9Fk4p39r"));
            songRepository.save(new Song("GBAHT0500599","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","City of Delusion",288,"3Sno9FE8r2uz8QP0MtnTPL"));
            songRepository.save(new Song("GBAHT0500598","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Hoodoo",223,"0EkE0ripJ9OFNzvZANzo5C"));
            songRepository.save(new Song("GBAHT0500600","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Knights of Cydonia",366,"7ouMYWpwJ422jRcDASZB7P"));
            songRepository.save(new Song("GBAHT0600546","9c9f1380-2516-4fc9-a3e6-f9f61941d090","af2e8e23-e9c3-4e67-8ad8-66387c5898fd","Rock","Glorious",281,"6IfitwQQ1Gu9g9QnLWDHRY"));

            songRepository.save(new Song("GBAHT0900320","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Uprising",304,"4VqPOruhp5EdPBeR92t6lQ"));
            songRepository.save(new Song("GBAHT0900321","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Resistance",346,"1C2QJNTmsTxCDBuIgai8QV"));
            songRepository.save(new Song("GBAHT0900322","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Undisclosed Desires",235,"0It6VJoMAare1zdV2wxqZq"));
            songRepository.save(new Song("GBAHT0900323","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","United States of Eurasia (+Collateral Damage)",347,"0tHbQRjL5phd8OoYl2Bdnd"));
            songRepository.save(new Song("GBAHT0900324","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Guiding Light",253,"7jZ5A8bf63qYaUXfuGoxVk"));
            songRepository.save(new Song("GBAHT0900325","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Unnatural Selection",414,"28FJMlLUu9NHuwlZWFKDn7"));
            songRepository.save(new Song("GBAHT0900326","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","MK Ultra",246,"0MrkZz4D3fGlEkhebjPPrh"));
            songRepository.save(new Song("GBAHT0900327","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","I Belong to You (+Mon Coeur S'Ouvre a Ta Voix)",338,"114rzL6VEy9bb3amPcY3tw"));
            songRepository.save(new Song("GBAHT0900328","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Exogenesis: Symphony Pt. 1 (Overture)",258,"6zkhhG8iQ8waiwGkQuhoE1"));
            songRepository.save(new Song("GBAHT0900329","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Exogenesis: Symphony Pt. 2 (Cross-pollination)",236,"39kUTBz4uJoy5VtmIybz9D"));
            songRepository.save(new Song("GBAHT0900330","9c9f1380-2516-4fc9-a3e6-f9f61941d090","8411a4db-e104-4e45-995f-24b2f1849437","Rock","Exogenesis: Symphony Pt. 3 (Redemption)",287,"76ZDwA8uTyMys4LIS3pBUX"));
        };
    }




}
