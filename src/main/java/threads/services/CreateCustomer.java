package threads.services;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import threads.entities.CustomerEntity;

public class CreateCustomer {
    final Random r = new Random();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    
    private CustomerEntity createCustomer() {
        double money = r.nextDouble();

        CustomerEntity newCustomer = new CustomerEntity(money);

        return newCustomer;
    }

    public Future<CustomerEntity> createCustomerWithTimeout(int seconds) {  
        return executor.submit(() -> {
            Thread.sleep(seconds);
            
            return createCustomer();
        });
    }
}
