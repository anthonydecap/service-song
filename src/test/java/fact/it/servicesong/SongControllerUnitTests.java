package fact.it.servicesong;

import fact.it.servicesong.model.Song;
import fact.it.servicesong.repository.SongRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SongControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SongRepository songRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenSong_whenGetSongByISRC_thenReturnJsonReview() throws Exception {
        Song song1Album1 = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",120,"url");

        given(songRepository.findSongByISRC("ISRC7875455454")).willReturn(song1Album1);

        mockMvc.perform(get("/songs/{ISRC}","ISRC7875455454"))
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
    public void givenSong_whenGetSongsByISRC_thenReturnJsonReviews() throws Exception {
        Song song1Album1 = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",120,"url");
        Song song2Album1 = new Song("ISRC7875455455","MBID111","MAID1","Rock","De DO DO",160,"url");

        List<Song> reviewList = new ArrayList<>();
        reviewList.add(song1Album1);
        reviewList.add(song2Album1);

        given(songRepository.findSongsByMBID("MBID111")).willReturn(reviewList);

        mockMvc.perform(get("/songs/artist/{MBID}","MBID111"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$[0].mbid", is("MBID111")))
                .andExpect(jsonPath("$[0].maid", is("MAID1")))
                .andExpect(jsonPath("$[0].genre", is("Rock")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[0].url", is("url")))

                .andExpect(jsonPath("$[1].isrc", is("ISRC7875455455")))
                .andExpect(jsonPath("$[1].mbid", is("MBID111")))
                .andExpect(jsonPath("$[1].maid", is("MAID1")))
                .andExpect(jsonPath("$[1].genre", is("Rock")))
                .andExpect(jsonPath("$[1].title", is("De DO DO")))
                .andExpect(jsonPath("$[1].length", is(160)))
                .andExpect(jsonPath("$[1].url", is("url")));
    }

    @Test
    public void givenSong_whenGetSongsByMAID_thenReturnJsonReviews() throws Exception {
        Song song1Album1 = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",120,"url");
        Song song2Album1 = new Song("ISRC7875455455","MBID111","MAID1","Rock","De DO DO",160,"url");

        List<Song> reviewList = new ArrayList<>();
        reviewList.add(song1Album1);
        reviewList.add(song2Album1);

        given(songRepository.findSongsByMAID("MAID1")).willReturn(reviewList);

        mockMvc.perform(get("/songs/album/{MAID}","MAID1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$[0].mbid", is("MBID111")))
                .andExpect(jsonPath("$[0].maid", is("MAID1")))
                .andExpect(jsonPath("$[0].genre", is("Rock")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[0].url", is("url")))

                .andExpect(jsonPath("$[1].isrc", is("ISRC7875455455")))
                .andExpect(jsonPath("$[1].mbid", is("MBID111")))
                .andExpect(jsonPath("$[1].maid", is("MAID1")))
                .andExpect(jsonPath("$[1].genre", is("Rock")))
                .andExpect(jsonPath("$[1].title", is("De DO DO")))
                .andExpect(jsonPath("$[1].length", is(160)))
                .andExpect(jsonPath("$[1].url", is("url")));
    }

    @Test
    public void givenSong_whenGetSongsByGenre_thenReturnJsonReviews() throws Exception {
        Song song1Album1 = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",120,"url");
        Song song2Album1 = new Song("ISRC7875455455","MBID111","MAID1","Rock","De DO DO",160,"url");

        List<Song> reviewList = new ArrayList<>();
        reviewList.add(song1Album1);
        reviewList.add(song2Album1);

        given(songRepository.findSongsByGenre("Rock")).willReturn(reviewList);

        mockMvc.perform(get("/songs/genre/{genre}","Rock"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$[0].mbid", is("MBID111")))
                .andExpect(jsonPath("$[0].maid", is("MAID1")))
                .andExpect(jsonPath("$[0].genre", is("Rock")))
                .andExpect(jsonPath("$[0].title", is("Roxanne")))
                .andExpect(jsonPath("$[0].length", is(120)))
                .andExpect(jsonPath("$[0].url", is("url")))

                .andExpect(jsonPath("$[1].isrc", is("ISRC7875455455")))
                .andExpect(jsonPath("$[1].mbid", is("MBID111")))
                .andExpect(jsonPath("$[1].maid", is("MAID1")))
                .andExpect(jsonPath("$[1].genre", is("Rock")))
                .andExpect(jsonPath("$[1].title", is("De DO DO")))
                .andExpect(jsonPath("$[1].length", is(160)))
                .andExpect(jsonPath("$[1].url", is("url")));
    }

    @Test
    public void whenPostSong_thenReturnJsonReview() throws Exception{
        Song song3Album1 = new Song("ISRC2275455454","MBID111","MAID1","Rock","Message In A Bottle",160,"url");

        mockMvc.perform(post("/songs")
                .content(mapper.writeValueAsString(song3Album1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("ISRC2275455454")))
                .andExpect(jsonPath("$.mbid", is("MBID111")))
                .andExpect(jsonPath("$.maid", is("MAID1")))
                .andExpect(jsonPath("$.genre", is("Rock")))
                .andExpect(jsonPath("$.title", is("Message In A Bottle")))
                .andExpect(jsonPath("$.length", is(160)))
                .andExpect(jsonPath("$.url", is("url")));
    }

    @Test
    public void givenSong_whenPutSong_thenReturnJsonReview() throws Exception{
        Song song1Album1 = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",120,"url");

        given(songRepository.findSongByISRC("ISRC7875455454")).willReturn(song1Album1);

        Song updatedSong = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",160,"url");

        mockMvc.perform(put("/songs")
                .content(mapper.writeValueAsString(updatedSong))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isrc", is("ISRC7875455454")))
                .andExpect(jsonPath("$.mbid", is("MBID111")))
                .andExpect(jsonPath("$.maid", is("MAID1")))
                .andExpect(jsonPath("$.genre", is("Rock")))
                .andExpect(jsonPath("$.title", is("Roxanne")))
                .andExpect(jsonPath("$.length", is(160)))
                .andExpect(jsonPath("$.url", is("url")));
    }

    @Test
    public void givenSong_whenDeleteSong_thenStatusOk() throws Exception{
        Song songToBeDeleted = new Song("ISRC7875455454","MBID111","MAID1","Rock","Roxanne",120,"url");

        given(songRepository.findSongByISRC("ISRC7875455454")).willReturn(songToBeDeleted);

        mockMvc.perform(delete("/songs/{ISRC}","ISRC7875455454")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenNoSong_whenDeleteSong_thenStatusNotFound() throws Exception{
        given(songRepository.findSongByISRC("ISRC7875455454")).willReturn(null);

        mockMvc.perform(delete("/songs/{ISRC}","ISRC7875455454")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
