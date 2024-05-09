package nsu.leorita.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InternalCrackRequestBody {

    public InternalCrackRequestBody(String id, String hash, int maxLength, int workersCount, int workerNumber) {
        this.id = id;
        this.hash = hash;
        this.maxLength = maxLength;
        this.workersCount = workersCount;
        this.workerNumber = workerNumber;
    }
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

    public int getWorkerNumber() {
        return workerNumber;
    }
}
