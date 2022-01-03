package fact.it.servicesong;

import com.fasterxml.jackson.databind.ObjectMapper;
import fact.it.servicesong.model.Song;
import fact.it.servicesong.repository.SongRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SongControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SongRepository songRepository;

    private Song song1Album1 = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",120,"url");
    private Song song2Album1 = new Song("ISRC7875455455","MBID111","MAID1","Rock","De DO DO",160,"url");
    private Song song1Album2 = new Song("ISRC6242455454","MBID222","MAID2","rap","Goosbumps",150,"url");
    private Song song2Album2 = new Song("ISRC6242455455","MBID222","MAID2","rap","Highest In The Room",140,"url");
    private Song song1Album3 = new Song("ISRC4442455455","MBID333","MAID3","jazz","Pikelar",340,"url");


    @BeforeEach
    public void beforeAllTests() {
        songRepository.deleteAll();
        songRepository.save(song1Album1);
        songRepository.save(song2Album1);
        songRepository.save(song1Album2);
        songRepository.save(song2Album2);
        songRepository.save(song1Album3);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        songRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenSong_whenGetSongByISRC() throws Exception {

        mockMvc.perform(get("/songs/{ISRC}",  "ISRC7875455454"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$.mbid", is("MBID111")))
                .andExpect(jsonPath("$.maid", is("MAID1")))
                .andExpect(jsonPath("$.genre", is("Rock")))
                .andExpect(jsonPath("$.title", is("Roxanne")))
                .andExpect(jsonPath("$.length", is(120)))
                .andExpect(jsonPath("$.url", is("url")));
    }

    @Test
    public void givenSong_whenGetSongsByMBID_thenReturnJsonSongs() throws Exception {

        List<Song> songList = new ArrayList<>();
        songList.add(song1Album1);
        songList.add(song1Album2);

        mockMvc.perform(get("/songs/artist/{MBID}", "MBID111"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$[0].mbid", is("MBID111")))
                .andExpect(jsonPath("$[0].mbid", is("MAID1")))
                .andExpect(jsonPath("$[0].genre", is("Rock")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[0].url", is("url")))

                .andExpect(jsonPath("$[1].isrc", is("ISRC7875455455")))
                .andExpect(jsonPath("$[1].mbid", is("MBID111")))
                .andExpect(jsonPath("$[1].mbid", is("MAID1")))
                .andExpect(jsonPath("$[1].genre", is("Rock")))
                .andExpect(jsonPath("$[1].title", is("De DO DO")))
                .andExpect(jsonPath("$[1].length", is(160)))
                .andExpect(jsonPath("$[1].url", is("url")));
    }

    @Test
    public void givenSong_whenGetSongsByMAID_thenReturnJsonSongs() throws Exception {

        List<Song> songList = new ArrayList<>();
        songList.add(song1Album1);
        songList.add(song1Album2);

        mockMvc.perform(get("/songs/album/{MAID}", "MAID1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$[0].mbid", is("MBID111")))
                .andExpect(jsonPath("$[0].mbid", is("MAID1")))
                .andExpect(jsonPath("$[0].genre", is("Rock")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[0].url", is("url")))

                .andExpect(jsonPath("$[1].isrc", is("ISRC7875455455")))
                .andExpect(jsonPath("$[1].mbid", is("MBID111")))
                .andExpect(jsonPath("$[1].mbid", is("MAID1")))
                .andExpect(jsonPath("$[1].genre", is("Rock")))
                .andExpect(jsonPath("$[1].title", is("De DO DO")))
                .andExpect(jsonPath("$[1].length", is(160)))
                .andExpect(jsonPath("$[1].url", is("url")));
    }

    @Test
    public void givenSong_whenGetSongsByGenre_thenReturnJsonSongs() throws Exception {

        List<Song> songList = new ArrayList<>();
        songList.add(song1Album1);
        songList.add(song1Album2);

        mockMvc.perform(get("/songs/genre/{genre}", "Rock"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$[0].mbid", is("MBID111")))
                .andExpect(jsonPath("$[0].mbid", is("MAID1")))
                .andExpect(jsonPath("$[0].genre", is("Rock")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[0].url", is("url")))

                .andExpect(jsonPath("$[1].isrc", is("ISRC7875455455")))
                .andExpect(jsonPath("$[1].mbid", is("MBID111")))
                .andExpect(jsonPath("$[1].mbid", is("MAID1")))
                .andExpect(jsonPath("$[1].genre", is("Rock")))
                .andExpect(jsonPath("$[1].title", is("De DO DO")))
                .andExpect(jsonPath("$[1].length", is(160)))
                .andExpect(jsonPath("$[1].url", is("url")));
    }


    @Test
    public void whenPostSong_thenReturnJsonSong() throws Exception {
        Song song2Album3 = new Song("ISRC2875455454","MBID333","MAID3","jazz","Roxanne",120,"url");

        mockMvc.perform(post("/songs")
                .content(mapper.writeValueAsString(song2Album3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.isrc", is("ISRC2875455454")))
                .andExpect(jsonPath("$.mbid", is("MBID333")))
                .andExpect(jsonPath("$.maid", is("MAID3")))
                .andExpect(jsonPath("$.genre", is("jazz")))
                .andExpect(jsonPath("$.title", is("Roxanne")))
                .andExpect(jsonPath("$.length", is(120)))
                .andExpect(jsonPath("$.url", is("url")));
    }

    @Test
    public void givenSong_whenPutSong_thenReturnJsonSong() throws Exception {

        Song updatedSong = new Song("ISRC7875455454","MBID111","MAID1","Rock","Message In A Bottle",120,"url");


        mockMvc.perform(put("/songs")
                .content(mapper.writeValueAsString(updatedSong))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$.mbid", is("MBID111")))
                .andExpect(jsonPath("$.maid", is("MAID3")))
                .andExpect(jsonPath("$.genre", is("Rock")))
                .andExpect(jsonPath("$.title", is("Message In A Bottle")))
                .andExpect(jsonPath("$.length", is(120)))
                .andExpect(jsonPath("$.url", is("url")));
    }

    @Test
    public void givenSong_whenDeleteSong_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/songs/{ISRC}","ISRC7875455454")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoSong_whenDeleteSong_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/songs/{ISRC}", "ISRC1958255454")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
