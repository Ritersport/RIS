package nsu.leorita.worker.controllers;

import jakarta.validation.Valid;
import nsu.leorita.worker.Worker;
import nsu.leorita.worker.model.CrackRequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class WorkerController {
    @PostMapping("/internal/api/worker/hash/crack/task")
    public ResponseEntity managerPost(@Valid @RequestBody CrackRequestBody body) {
        Worker worker = new Worker();
        System.out.println(String.format("worker get %d", body.getWorkerNumber()));
        worker.doJob(body);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
