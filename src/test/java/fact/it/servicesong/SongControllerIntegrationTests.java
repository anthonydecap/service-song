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

    @BeforeEach
    public void beforeAllTests() {
        songRepository.deleteAll();
        songRepository.save(song1Album1);
        songRepository.save(song2Album1);
        songRepository.save(song1Album2);
        songRepository.save(song2Album2);
    }

    @AfterEach
    public void afterAllTests() {
        //Watch out with deleteAll() methods when you have other data in the test database!
        songRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenSong_whenGetSongByISBNAndName_thenReturnJsonSong() throws Exception {

        mockMvc.perform(get("/songs/album/{ISRC}/{title}/", "7875455454", "Roxanne"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("7875455454")))
                .andExpect(jsonPath("$.title", is("Roxanne")))
                .andExpect(jsonPath("$.legth", is(120)));
    }
}
