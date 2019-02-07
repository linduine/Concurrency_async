import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestWithoutGet {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
// 2. WRITE THE SECOND PROGRAM THAT USES COMPLETABLE FUTURE TO ACHIEVE THE SAME THING

        List<String> myList = Arrays.asList("one", "two", "three");
        List<String> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (String temp : myList) {
            CompletableFuture<String> str = CompletableFuture.completedFuture(temp).handleAsync((rs, e) -> {
                        try {
                            futures.add(rs + " modified " + Thread.currentThread().getName());
                            return null;
                        } catch(Exception ex) { return  null; }},
                    executorService);
        }

        while(futures.size() < myList.size()) {
            continue;
        }
        System.out.println(futures);
        executorService.shutdown();
    }
}
