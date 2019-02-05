import org.omg.CORBA.SystemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
// 2. WRITE THE SECOND PROGRAM THAT USES COMPLETABLE FUTURE TO ACHIEVE THE SAME THING

        List<String> myList = Arrays.asList("one", "two", "three");
        List<CompletableFuture<String>> futures = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (String temp : myList) {
           // Printer2 task = new Printer2(temp);
            CompletableFuture<String> str = CompletableFuture.completedFuture(temp).handleAsync((rs, e) -> {
                try {
                    //return task.call();
                return rs + " modified " + Thread.currentThread().getName();
                } catch(Exception ex) { return  null; }},
                    executorService);
            futures.add(str);
        }

        for (CompletableFuture future : futures ) {
            System.out.println(future.get());
        }
        executorService.shutdown();
    }
}
