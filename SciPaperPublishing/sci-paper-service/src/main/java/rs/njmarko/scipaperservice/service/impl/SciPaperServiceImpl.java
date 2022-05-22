package rs.njmarko.scipaperservice.service.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.njmarko.scipaperservice.messages.PublishMessage;
import rs.njmarko.scipaperservice.model.PublishPaperRequest;
import rs.njmarko.scipaperservice.model.SciPaper;
import rs.njmarko.scipaperservice.repository.SciPaperRepository;
import rs.njmarko.scipaperservice.service.SciPaperService;
import rs.njmarko.scipaperservice.service.resttemplates.UserService;

import java.util.Optional;

@Service
public class SciPaperServiceImpl implements SciPaperService {

    private final RabbitTemplate rabbitTemplate;

    private final SciPaperRepository sciPaperRepository;

    private final UserService userService;

    @Autowired
    public SciPaperServiceImpl(RabbitTemplate rabbitTemplate, SciPaperRepository sciPaperRepository, UserService userService) {
        this.rabbitTemplate = rabbitTemplate;
        this.sciPaperRepository = sciPaperRepository;
        this.userService = userService;
    }

    @Override
    public SciPaper createSciPaper(SciPaper sciPaper) {
        // check login
        if (userService.isLoggedIn(sciPaper.getAuthor()).equals(Boolean.FALSE)) {
            throw new IllegalArgumentException("User " + sciPaper.getAuthor() + " is not logged in!");
        }

        sciPaperRepository.save(sciPaper);

        return sciPaper;
    }

    @Override
    public String publishPaper(PublishPaperRequest publishPaperRequest) {
        // check login
        if (userService.isLoggedIn(publishPaperRequest.getUsername()).equals(Boolean.FALSE)) {
            throw new IllegalArgumentException("User " + publishPaperRequest.getUsername() + " is not logged in!");
        }

        // fetch title
        Optional<SciPaper> paper = sciPaperRepository.findById(publishPaperRequest.getId());
        SciPaper sciPaper = paper.orElseThrow(() -> new IllegalArgumentException("Paper with id " + publishPaperRequest.getId() + " doesn't exist!"));

        // fetch full name
        String name = userService.getFullName(publishPaperRequest.getUsername());
        if (name.equals("")) {
            throw new IllegalArgumentException("Service is not available. Try again later!");
        }

        if (!publishPaperRequest.getUsername().equals(sciPaper.getAuthor())){
            throw new IllegalArgumentException("You can't publish this paper because you did not create it!");
        }

        PublishMessage publishMessage = new PublishMessage();
        publishMessage.setId(publishPaperRequest.getId());
        publishMessage.setAuthor(name);
        publishMessage.setTitle(sciPaper.getTitle());

        rabbitTemplate.convertAndSend("", publishMessage);

        return "Paper published!";
    }
}
