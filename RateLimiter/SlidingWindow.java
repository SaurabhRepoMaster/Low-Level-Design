import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlidingWindow {

    private Queue<Long> window;
    private int timeWindow;
    private int bucketCapacity;

    public SlidingWindow(int timeWindow,int bucketCapacity)
    {
        this.timeWindow=timeWindow;
        this.bucketCapacity=bucketCapacity;
        window = new ConcurrentLinkedQueue<>();
    }

    public Boolean grantAccess(){
        long currentTime = System.currentTimeMillis();
        checkAndUpdateWindow(currentTime);
        if(window.size()<bucketCapacity)
        {
            window.offer(currentTime);
            return true;
        }
        return false;

    }

    private void checkAndUpdateWindow(long currentTime) {
        if(window.isEmpty())
            return;
        long calculateTime = (currentTime-window.peek())/1000;
        while(calculateTime>timeWindow)
        {
            window.poll();
            if(window.isEmpty())
                break;
            calculateTime=(currentTime-window.peek())/1000;
        }
    }


}
