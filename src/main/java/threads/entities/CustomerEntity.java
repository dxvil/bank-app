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

    @Override
    public int compareTo(CustomerEntity that) {
        return this.id.compareTo(that.id);
    }
}
