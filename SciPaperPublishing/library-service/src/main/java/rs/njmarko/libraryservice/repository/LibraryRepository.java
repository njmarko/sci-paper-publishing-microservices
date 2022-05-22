package rs.njmarko.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.njmarko.libraryservice.model.PublishedPaper;

public interface LibraryRepository extends JpaRepository<PublishedPaper, String> {
}
