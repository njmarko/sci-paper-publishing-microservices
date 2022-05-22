package rs.njmarko.scipaperservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import rs.njmarko.scipaperservice.model.SciPaper;

public interface SciPaperRepository extends MongoRepository<SciPaper, String> {
}
