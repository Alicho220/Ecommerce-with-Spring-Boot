package com.ecommerce.service.customer;

import com.ecommerce.data.model.Customer;

import javax.persistence.Id;
import java.util.List;

public interface CustomerService {

    public Customer saveCustomer(Customer customer);
    public Customer findByCustomerId(Integer Id);
    public List<Customer> findAllCustomer();
    public void deleteCustomerById (Integer Id);
    public Customer updateCustomer(Customer customer);


}
