package rs.njmarko.scipaperservice.messages;


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
