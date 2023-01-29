package threads.threads;

import java.util.concurrent.locks.ReentrantLock;

import threads.services.BankQueue;

public class TellersThread implements Runnable {
    final ReentrantLock reentrantLock = new ReentrantLock();
    private BankQueue customerQueue;

    public TellersThread(BankQueue customerQueue) {
        this.customerQueue = customerQueue;
    }

    @Override
    public void run() {
        while(true) {
            if(customerQueue.customerQueue.size() == 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                reentrantLock.lock();
                customerQueue.removeCustomerFromQueue();
                reentrantLock.unlock();
            }   
    }
}
}
