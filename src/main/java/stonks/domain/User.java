package stonks.domain;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public List<Goal> goals;
    
    @JsonCreator
    public User(@JsonProperty("name") String name, @JsonProperty("goals") List<Goal> goals) {
        this.name = name;
        this.goals = goals;
    }

    public User(String name) {
        this.name = name;
        this.goals = new ArrayList<>();
    }
}
