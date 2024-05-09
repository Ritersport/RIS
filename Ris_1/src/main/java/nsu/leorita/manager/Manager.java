package nsu.leorita.manager;

import nsu.leorita.manager.model.ClientCrackRequestBody;
import nsu.leorita.manager.model.InternalCrackRequestBody;
import nsu.leorita.manager.model.JobStatus;
import nsu.leorita.manager.model.WorkStatus;
import nsu.leorita.manager.services.WorkerService;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Manager {

    public static int WORKERS_COUNT;
    private static final AtomicInteger requestedWorkers = new AtomicInteger(0);
    private final WorkerService workerService = new WorkerService();
    private static final ConcurrentHashMap<UUID, JobStatus> requests = new ConcurrentHashMap<>();
    Timer timer = new Timer();

    public UUID addRequest(ClientCrackRequestBody request) {
        UUID id = UUID.randomUUID();
        JobStatus responseStatus = new JobStatus(WorkStatus.IN_PROGRESS.name(), new ArrayList<>());
        requests.put(id, responseStatus);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (requests.get(id).getStatus() == WorkStatus.IN_PROGRESS) {
                        requests.get(id).setStatus(WorkStatus.ERROR);
                    }
                }
        }, 600000);

        for (int i = 0; i < WORKERS_COUNT; i++) {
            System.out.println(String.format("send request to worker %d", i + 1));
            InternalCrackRequestBody body = new InternalCrackRequestBody(
                    id.toString(), request.getHash(), request.getMaxLength(), WORKERS_COUNT, i);
            workerService.doJob(body);
            System.out.println(String.format("sent request to worker %d", i + 1));

        }
        return id;
    }

    public static void updateRequest(String id, List<String> data) {
        int req = requestedWorkers.incrementAndGet();
        requests.get(UUID.fromString(id)).getData().addAll(data);
        System.out.println(String.format("get response to worker %d", req));
        if (req == WORKERS_COUNT) {
            requests.get(UUID.fromString(id)).setStatus(WorkStatus.READY);
        }
    }

    public static JobStatus getRequest(String id) {
        return requests.get(UUID.fromString(id));
    }
}
