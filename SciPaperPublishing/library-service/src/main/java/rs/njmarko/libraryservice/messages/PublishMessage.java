package rs.njmarko.libraryservice.messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PublishMessage implements Serializable {

    private String id;

    private String author;

    private String title;
}
