package rs.njmarko.scipaperservice.service;

import rs.njmarko.scipaperservice.model.PublishPaperRequest;
import rs.njmarko.scipaperservice.model.SciPaper;

public interface SciPaperService {
    SciPaper createSciPaper(SciPaper sciPaper);

    String publishPaper(PublishPaperRequest publishPaperRequest);
}
