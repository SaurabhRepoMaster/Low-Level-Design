import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String args[])
    {
        UserBucket userBucket= new UserBucket(1);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i  = 0; i<15;i++)
        {
            executorService.execute(()->userBucket.accessApplication(1));
        }
        executorService.shutdown();
    }
}
