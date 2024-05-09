package nsu.leorita.worker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class PatchBody {

    public PatchBody(String id, ArrayList<String> data) {
        this.id = id;
        this.data = data;
    }

    public PatchBody() {}
        @JsonProperty
        private String id;

        @JsonProperty
        private ArrayList<String> data;

}
