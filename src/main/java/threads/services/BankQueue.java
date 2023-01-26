package threads.services;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import threads.entities.CustomerEntity;

public class BankQueue {
  volatile public Queue<CustomerEntity> customerQueue = new PriorityBlockingQueue<>();

  public void addCustomerToQueue(CustomerEntity newCustomer) {
        if(newCustomer != null) {
            customerQueue.add(newCustomer);
            System.out.printf("Customer %s added to queue.", newCustomer.id);
            return;
        }
        String errorMsg = "You tried to add an empty customer value. That's not allowed.";
        
        throw new IllegalArgumentException(errorMsg) ;
    }

    public void removeCustomerFromQueue() {
        CustomerEntity customer = customerQueue.poll();

        System.out.println(customer + "  " + "is removing");

        System.out.printf("Customer %s serviced.", customer.id);
    }
}
