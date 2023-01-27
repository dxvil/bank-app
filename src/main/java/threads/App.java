package threads;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import threads.services.BankQueue;
import threads.threads.CustomerThread;
import threads.threads.TellersThread;

public class App 
{
    public static void main( String[] args )
    {
        final BankQueue customerQueue = new BankQueue();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        
        executor.execute(new CustomerThread(customerQueue));
        executor.execute(new TellersThread(customerQueue));
        executor.shutdown();
    }
}
