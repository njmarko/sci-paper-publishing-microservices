package rs.njmarko.libraryservice.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.njmarko.libraryservice.messages.PublishMessage;
import rs.njmarko.libraryservice.model.PublishedPaper;
import rs.njmarko.libraryservice.service.LibraryService;

import java.util.List;

@RestController
public class LibraryController {


    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


    @GetMapping("all")
    public List<PublishedPaper> getAllPapers(){
        return libraryService.findAll();
    }

    @RabbitListener(queues = "PUBLISH_PAPER")
    public void publishPaperListener(PublishMessage publishMessage){
        libraryService.publishPaper(publishMessage);
    }
}
