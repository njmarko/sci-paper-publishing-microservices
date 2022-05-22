package rs.njmarko.scipaperservice.messages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublishMessage implements Serializable {

    private String id;

    private String author;

    private String title;
}
