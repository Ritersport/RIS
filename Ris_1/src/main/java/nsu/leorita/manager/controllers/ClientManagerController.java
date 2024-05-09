package nsu.leorita.manager.controllers;

import jakarta.validation.Valid;
import nsu.leorita.manager.model.ClientCrackRequestBody;
import nsu.leorita.manager.model.JobStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import nsu.leorita.manager.Manager;
import nsu.leorita.manager.model.ManagerResponseId;

@RestController
public class ClientManagerController {

    private final Manager manager = new Manager();
    @RequestMapping("/api/hash/status")
    public JobStatus jobStatusGet(@RequestParam(value= "requestId") String requestId) {
        return Manager.getRequest(requestId);
    }

    @PostMapping("/api/hash/crack")
    public ResponseEntity<ManagerResponseId> managerPost(@Valid @RequestBody ClientCrackRequestBody body) {
        String id = manager.addRequest(body).toString();
        ManagerResponseId response = new ManagerResponseId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
