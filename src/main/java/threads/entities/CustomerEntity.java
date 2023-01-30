package threads.entities;

import java.util.UUID;

public class CustomerEntity implements Comparable<CustomerEntity> {
    /* Using of uuid as id is a guarantee of unique value.  */
    public UUID id;
    protected double amountOfMoney;
    
    public CustomerEntity(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public double getAmountOfMoney() {
        return this.amountOfMoney;
    }

    public void setAmountOfMoney(double amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }
   
    @Override
    public int compareTo(CustomerEntity that) {
        return this.id.compareTo(that.id);
    }
}
