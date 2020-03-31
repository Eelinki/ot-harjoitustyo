package stonks.domain;

import com.fasterxml.jackson.annotation.*;

public class User {
    public String name;
    
    @JsonCreator
    public User(@JsonProperty("name") String name) {
        this.name = name;
    }
}
