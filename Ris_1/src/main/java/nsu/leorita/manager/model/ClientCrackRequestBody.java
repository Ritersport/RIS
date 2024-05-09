package nsu.leorita.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ClientCrackRequestBody implements Serializable {

    @JsonProperty
    private String hash;

    @JsonProperty
    private int maxLength;

    public int getMaxLength() {
        return maxLength;
    }

    public String getHash() {
        return hash;
    }
}
