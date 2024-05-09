package nsu.leorita.worker;

import nsu.leorita.worker.model.CrackRequestBody;
import nsu.leorita.worker.model.PatchBody;
import org.paukov.combinatorics.Generator;
import org.paukov.combinatorics.ICombinatoricsVector;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static org.paukov.combinatorics.CombinatoricsFactory.*;

public class Worker {
    static ConcurrentHashMap<String, ArrayList<String>> words = new ConcurrentHashMap<>();

    public void doJob(CrackRequestBody body) {
        new Thread(() -> {
            words.put(body.getId(), new ArrayList<>());
            ICombinatoricsVector<Character> vector = createVector('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
                    'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                    'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
            int vectorSize = vector.getSize();
            int onePackSize = vectorSize / body.getWorkersCount();
            int minPos = body.getWorkerNumber() * onePackSize;
            if (body.getWorkersCount() == body.getWorkerNumber() + 1)
            {
                onePackSize += vectorSize % body.getWorkersCount();
            }
            int maxPos = minPos + onePackSize - 1;
            System.out.printf("minPos: %d maxPos: %d%n", minPos, maxPos);

            for (int i = 1; i < body.getMaxLength(); i++) {
                Generator<Character> gen = createPermutationWithRepetitionGenerator(vector, i);
                for (ICombinatoricsVector<Character> combination : gen) {
                    for (int firstLetter = minPos; firstLetter <= maxPos; firstLetter++) {
                        String str = getStringFromCharVector(combination, vector.getValue(firstLetter));
                        try {
                            String hash = getMD5Hash(str);
                            if (Objects.equals(hash, body.getHash())) {
                                words.get(body.getId()).add(str);
                            }
                        } catch (NoSuchAlgorithmException ignored) {}
                    }
                }
            }
            for (int firstLetter = minPos; firstLetter <= maxPos; firstLetter++) {
                String str = String.valueOf(firstLetter);
                try {
                    String hash = getMD5Hash(str);
                    if (Objects.equals(hash, body.getHash())) {
                        words.get(body.getId()).add(str);
                    }
                } catch (NoSuchAlgorithmException ignored) {}
            }
            patch(new PatchBody(body.getId(), words.get(body.getId())));
        }).start();
    }

    private final String managerUrl
            = "http://manager:8080/internal/api/manager/hash/crack/request";
    public void patch(PatchBody patchBody) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        HttpEntity<PatchBody> entity = new HttpEntity<>(patchBody);
        restTemplate.patchForObject(managerUrl, entity, PatchBody.class);
    }

    private static String getStringFromCharVector(ICombinatoricsVector<Character> vector, Character firstLetter) {
        final String[] res = {String.valueOf(firstLetter)};
        vector.forEach(it -> res[0] = res[0].concat(it.toString()));
        return res[0];
    }

    private static String getMD5Hash(String word) throws NoSuchAlgorithmException {
        byte[] bytesOfMessage = word.getBytes(StandardCharsets.UTF_8);

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theMD5digest = md.digest(bytesOfMessage);
        BigInteger bigInt = new BigInteger(1, theMD5digest);
        return bigInt.toString(16);
    }
}
