import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Printer2 implements Callable<String> {
    String str;

    public Printer2(String str) {
        this.str = str;
    }

    @Override
    public String call() throws Exception {
        return str + " modified by " + Thread.currentThread().getName();
    }
}

