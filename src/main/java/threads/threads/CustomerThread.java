package threads.threads;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

import threads.entities.CustomerEntity;
import threads.services.BankQueue;
import threads.services.CreateCustomer;

public class CustomerThread implements Runnable {
    final CreateCustomer createCustomer = new CreateCustomer();
    final ReentrantLock reentrantLock = new ReentrantLock();
    private BankQueue customerQueue;

    public CustomerThread(BankQueue customerQueue) {
        this.customerQueue = customerQueue;

    }

    @Override
    public void run() {
        while(true) {
            int seconds = (ThreadLocalRandom.current().nextInt(60 + 1 - 1) + 1) * 100;

            System.out.println(seconds);

            Future<CustomerEntity> customer = 
            createCustomer.createCustomerWithTimeout(seconds);

            CustomerEntity customerResult = null;

            try {
                Thread.sleep(seconds);
                customerResult = customer.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            if(customerResult != null) {
                reentrantLock.lock();
                customerQueue.addCustomerToQueue(customerResult);
                reentrantLock.unlock();
            }
        }
    }
}
