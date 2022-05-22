package rs.njmarko.libraryservice.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.njmarko.libraryservice.messages.PublishMessage;
import rs.njmarko.libraryservice.service.LibraryService;


@Component
public class MessageListener {

    private final LibraryService libraryService;

    @Autowired
    public MessageListener(LibraryService libraryService) {
        this.libraryService = libraryService;
    }


}
