package nsu.leorita.manager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class JobStatus {

    @JsonProperty
    private String status;

    @JsonProperty
    private ArrayList<String> data;

    public JobStatus(WorkStatus status, ArrayList<String> data) {
        this.data = data;
        this.status = status.name();
    }

    public JobStatus(String status, ArrayList<String> data) {
        this.data = data;
        this.status = status;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setStatus(WorkStatus status) {
        this.status = status.name();
    }

    public WorkStatus getStatus() {
        return WorkStatus.valueOf(status);
    }
}
