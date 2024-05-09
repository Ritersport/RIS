package nsu.leorita.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class InternalCrackResponseBody {
    @JsonProperty
    private String id;

    @JsonProperty
    private ArrayList<String> data;

    public String getId() {
        return id;
    }

    public ArrayList<String> getData() {
        return data;
    }
}
