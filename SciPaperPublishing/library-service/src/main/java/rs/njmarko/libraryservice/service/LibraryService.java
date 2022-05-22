package rs.njmarko.libraryservice.service;

import rs.njmarko.libraryservice.messages.PublishMessage;
import rs.njmarko.libraryservice.model.PublishedPaper;

import java.util.List;

public interface LibraryService {
    List<PublishedPaper> findAll();

    void publishPaper(PublishMessage publishMessage);
}
