package p01;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

    private String ISBN;
    private String authorFirstname;
    private String authorLastname;
    private String title;

    @JsonCreator
    public Book() {

    }

}
