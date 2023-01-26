package threads.threads;

import java.util.concurrent.locks.ReentrantLock;

import threads.services.BankQueue;

public class TellersThread implements Runnable {
    final BankQueue customerQueue = new BankQueue();
    ReentrantLock reentrantLock;
    
    public TellersThread(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(customerQueue.customerQueue.size() + " size");
            try {
                if(customerQueue.customerQueue.size() != 0) {
                    reentrantLock.lock();
                    System.out.println("Removin");
                    removeCustomerFromQueue();
                    System.out.println(customerQueue.customerQueue);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private void removeCustomerFromQueue() {
            customerQueue.removeCustomerFromQueue();
    }
}
