package nsu.leorita.worker.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrackRequestBody {
    @JsonProperty
    private String id;

    @JsonProperty
    private String hash;

    @JsonProperty
    private int maxLength;

    @JsonProperty
    private int workersCount;

    @JsonProperty
    private int workerNumber;

    public String getId() {
        return id;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getHash() {
        return hash;
    }

    public int getWorkersCount() {
        return workersCount;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }
}