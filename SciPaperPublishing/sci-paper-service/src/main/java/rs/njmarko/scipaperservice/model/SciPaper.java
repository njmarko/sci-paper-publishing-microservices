package rs.njmarko.scipaperservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SciPaper {

    @Id
    private String id;

    private String author;

    private String title;

    private List<Section> sections = new ArrayList<>();
}
