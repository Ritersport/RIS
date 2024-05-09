package nsu.leorita.manager.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import nsu.leorita.manager.model.InternalCrackRequestBody;

public class WorkerService {

    RestTemplate restTemplate = new RestTemplate();
    public WorkerService() {
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }
    private static final String crackResourceUrlRaw
            = "http://worker:8080/internal/api/worker/hash/crack/task";

    public void doJob(InternalCrackRequestBody job) {
        HttpEntity<InternalCrackRequestBody> entity = new HttpEntity<>(job);
        restTemplate.postForLocation(crackResourceUrlRaw, entity, InternalCrackRequestBody.class);
    }
}
