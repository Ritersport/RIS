package nsu.leorita.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ManagerResponseId {

    @JsonProperty
    private String requestId;

    public ManagerResponseId(String requestId) {
        this.requestId = requestId;
    }

}
