package rs.njmarko.libraryservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "published_papers")
public class PublishedPaper {

    @Id
    @NotBlank(message = "Field 'id' cannot be empty!")
    private String id;

    @Column(name = "author", nullable = false)
    @NotBlank(message = "Field 'author' cannot be empty!")
    private String author;

    @Column(name = "title", nullable = false)
    @NotBlank(message = "Field 'title' cannot be empty!")
    private String title;
}
