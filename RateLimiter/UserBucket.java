import java.util.HashMap;
import java.util.Map;

public class UserBucket {

    private Map<Integer,SlidingWindow> bucket;
    public UserBucket(int id)
    {
        bucket=new HashMap<>();
        bucket.put(id,new SlidingWindow(1,5));
    }

    public void accessApplication(int id){
        if(bucket.get(id).grantAccess())
        {
            System.out.println(Thread.currentThread().getName()+": user is allowed to access the application");
        }
        else {
            System.out.println(Thread.currentThread().getName()+ "  429: Too many Requests");
        }
    }

}
