package graphDB.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GraphRequest {

    @JsonProperty("diagnosis")
    private String diagnosis;

    @JsonProperty("gender")
    private String gender;

    @JsonProperty("age")
    private int age;

    @JsonProperty("ethnicity")
    private String ethnicity;

    @JsonProperty("hpi")
    private String hpi;

    @JsonProperty("history")
    private String history;

    @JsonProperty("medicine")
    private String medicine;

    @JsonProperty("dosage")
    private String dosage;
}
