package com.service.customer.repository;

import com.service.customer.model.Customer;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static com.service.customer.helper.TestData.getCustomerData;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
@ActiveProfiles("test")
public class CustomerRepositoryIT {

    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer = getCustomerData();

    @Test
    public void save_whenCustomerDataIsPassed_shouldStoreTheCustomerData() {
        customer.setFirstName("Iron");
        customer.setSurname("Man");

        Customer storedCustomer = customerRepository.save(customer);

        assertEquals("Iron", storedCustomer.getFirstName());
        assertEquals("Man", storedCustomer.getSurname());
    }

    @Test
    public void getOne_whenCustomerIdIsPassed_shouldReturnTheCustomerData() {
        customer.setFirstName("Steve");
        customer.setSurname("Rogers");
        Customer storedCustomer = customerRepository.save(customer);

        Customer actualCustomer = customerRepository.getOne(customer.getId());

        assertEquals(customer, actualCustomer);
    }
}
