package fact.it.servicesong.repository;

import fact.it.servicesong.model.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
    Song findSongByISRC(String ISRC);
    List<Song> findSongsByGenre(String genre);
    List<Song> findSongsByMBID(String IMDB);
}


