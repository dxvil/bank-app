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
    final BankQueue customerQueue = new BankQueue();
    ReentrantLock reentrantLock;
    final CreateCustomer createCustomer = new CreateCustomer();
    
    public CustomerThread(ReentrantLock reentrantLock) {
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        while(true) {
            reentrantLock.lock();
            try {
                int seconds = (r.nextInt(60 + 1 - 1) + 1) * 1000;
        
                System.out.println(seconds);
        
                Future<CustomerEntity> customer = createCustomer.createCustomerWithTimeout(seconds);
                CustomerEntity customerResult = null;
                try {
                    System.out.println("thread sleep");
                    reentrantLock.unlock();
                    Thread.sleep(seconds);
                } finally {
                    System.out.println("thread awake");
                    reentrantLock.lock();
                    customerResult = customer.get();
                }
        
                if(customerResult != null) {
                    System.out.println("get result");
                    
                    addCustomerToQueue(customerResult);
                }
        
            } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
            } finally {
                    reentrantLock.unlock();
            }
        }
    }

    private void addCustomerToQueue(CustomerEntity customerResult) {
        try {
            System.out.println("starting to add customer to queue");
            customerQueue.addCustomerToQueue(customerResult);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
