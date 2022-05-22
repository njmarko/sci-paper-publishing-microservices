package rs.njmarko.libraryservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.njmarko.libraryservice.messages.PublishMessage;
import rs.njmarko.libraryservice.model.PublishedPaper;
import rs.njmarko.libraryservice.repository.LibraryRepository;
import rs.njmarko.libraryservice.service.LibraryService;

import java.util.List;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public List<PublishedPaper> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public void publishPaper(PublishMessage publishMessage) {
        var paper = new PublishedPaper(publishMessage.getId(), publishMessage.getAuthor(), publishMessage.getTitle());
        libraryRepository.save(paper);
    }
}
