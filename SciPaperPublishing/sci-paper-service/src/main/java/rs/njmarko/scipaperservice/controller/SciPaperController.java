package rs.njmarko.scipaperservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import rs.njmarko.scipaperservice.model.PublishPaperRequest;
import rs.njmarko.scipaperservice.model.SciPaper;
import rs.njmarko.scipaperservice.service.SciPaperService;

import javax.validation.Valid;

@RestController
public class SciPaperController {

    private final SciPaperService sciPaperService;

    @Autowired
    public SciPaperController(SciPaperService sciPaperService) {
        this.sciPaperService = sciPaperService;
    }

    @PostMapping("create")
    public SciPaper createPaper(@Valid @RequestBody SciPaper sciPaper){
        return sciPaperService.createSciPaper(sciPaper);
    }

    @PostMapping("publish")
    public String publish(@RequestBody PublishPaperRequest publishPaperRequest){
        return sciPaperService.publishPaper(publishPaperRequest);
    }

}
