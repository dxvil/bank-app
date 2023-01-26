package threads;

import java.util.concurrent.locks.ReentrantLock;

import threads.services.BankQueue;
import threads.threads.CustomerThread;
import threads.threads.TellersThread;

public class App 
{
    public static void main( String[] args )
    {
        ReentrantLock reentrantLock = new ReentrantLock();
        Thread tellersThread = new Thread(new TellersThread(reentrantLock));
        Thread customerThread = new Thread(new CustomerThread(reentrantLock));
        
        customerThread.start();
        tellersThread.start();
    }
}
