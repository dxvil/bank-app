package threads.threads;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import threads.entities.CustomerEntity;
import threads.services.BankQueue;
import threads.services.CreateCustomer;

public class CustomerThread implements Runnable {
    final Random r = new Random();
    final CreateCustomer createCustomer = new CreateCustomer();
    ReentrantLock reentrantLock = new ReentrantLock();
    BankQueue customerQueue;

    public CustomerThread(BankQueue customerQueue) {
        this.customerQueue = customerQueue;
    }

    @Override
    public void run() {
        while(true) {
            int seconds = (r.nextInt(60 + 1 - 1) + 1) * 100;

            System.out.println(seconds);

            Future<CustomerEntity> customer = 
            createCustomer.createCustomerWithTimeout(seconds);

            CustomerEntity customerResult = null;

            try {
                Thread.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                try {
                    customerResult = customer.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }

            if(customerResult != null) {
                reentrantLock.lock();
                customerQueue.addCustomerToQueue(customerResult);
                reentrantLock.unlock();
            }
        }
    }
}
