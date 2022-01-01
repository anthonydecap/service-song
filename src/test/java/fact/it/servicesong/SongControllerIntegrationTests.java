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

    private Song song1Album1 = new Song("7875455454","Roxanne",120);
    private Song song2Album1 = new Song("7875455454","De DO DO",129);
    private Song song1Album2 = new Song("8452131444","Goosebumps",284);
    private Song song2Album2 = new Song("8452131444","Highest in the Room",180);
    private Song song1Album3 = new Song("7875455884","Roxanne",120);

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
    public void givenSong_whenGetSongByISBNAndName_thenReturnJsonSong() throws Exception {

        mockMvc.perform(get("/songs/album/{ISRC}/{title}", "7875455454", "Roxanne"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("7875455454")))
                .andExpect(jsonPath("$.title", is("Roxanne")))
                .andExpect(jsonPath("$.length", is(120)));
    }

    @Test
    public void givenSong_whenGetSongsByISRC_thenReturnJsonSongs() throws Exception {

        List<Song> songList = new ArrayList<>();
        songList.add(song1Album1);
        songList.add(song1Album2);

        mockMvc.perform(get("/songs/{ISRC}", "7875455454"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isrc", is("7875455454")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[1].isrc", is("7875455454")))
                .andExpect(jsonPath("$[1].title", is("De DO DO")))
                .andExpect(jsonPath("$[1].length", is(129)));
    }

    @Test
    public void givenSong_whenGetSongsByTitle_thenReturnJsonSongs() throws Exception {

        List<Song> songList = new ArrayList<>();
        songList.add(song1Album1);
        songList.add(song1Album3);

        mockMvc.perform(get("/songs/{title}", "Roxanne"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isrc", is("7875455454")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[1].isrc", is("7875455884")))
                .andExpect(jsonPath("$[1].title", is("Roxanne")))
                .andExpect(jsonPath("$[1].length", is(120)));
    }

    @Test
    public void whenPostSong_thenReturnJsonSong() throws Exception {
        Song song2Album3 = new Song("7875455884","Message in a bottle",156);

        mockMvc.perform(post("/songs")
                .content(mapper.writeValueAsString(song2Album3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("7875455884")))
                .andExpect(jsonPath("$.title", is("Message in a bottle")))
                .andExpect(jsonPath("$.length", is(156)));
    }

    @Test
    public void givenSong_whenPutSong_thenReturnJsonReview() throws Exception {

        Song updatedReview = new Song("7875455884","Message in a bottle",156);


        mockMvc.perform(put("/reviews")
                .content(mapper.writeValueAsString(updatedReview))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("7875455884")))
                .andExpect(jsonPath("$.title", is("Message in a bottle")))
                .andExpect(jsonPath("$.length", is(156)));
    }

    @Test
    public void givenSong_whenDeleteSong_thenStatusOk() throws Exception {

        mockMvc.perform(delete("/songs/album/{ISRC}/{title}", "7875455454", "Roxanne")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoSong_whenDeleteSong_thenStatusNotFound() throws Exception {

        mockMvc.perform(delete("/songs/album/{ISRC}/{title}", "1958255454", "BaBy ShArK")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
