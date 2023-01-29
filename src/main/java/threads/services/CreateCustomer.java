package threads.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

import threads.entities.CustomerEntity;

public class CreateCustomer {
    final private ExecutorService executor = Executors.newSingleThreadExecutor();
    
    private CustomerEntity createCustomer() {
        double money = ThreadLocalRandom.current().nextDouble();

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
