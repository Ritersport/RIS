package nsu.leorita.manager.controllers;

import jakarta.validation.Valid;
import nsu.leorita.manager.model.JobStatus;
import nsu.leorita.manager.model.WorkStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import nsu.leorita.manager.Manager;
import nsu.leorita.manager.model.InternalCrackResponseBody;

@RestController
public class InternalController {
    @PatchMapping("/internal/api/manager/hash/crack/request")
    public ResponseEntity<InternalCrackResponseBody> managerPatch(@Valid @RequestBody InternalCrackResponseBody body) {
        Manager.updateRequest(body.getId(), body.getData());
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
