package com.ecommerce.wed.controllers.customer;

import com.ecommerce.data.model.Customer;
import com.ecommerce.data.model.Product;
import com.ecommerce.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

//    @GetMapping("/all")
//    public List<Customer> getAllCustomer(){
//        return customerService.findAllCustomer();
//
//    }
    @GetMapping("/all")
    public  ResponseEntity<?> getAllCustomers (){
        List<Customer> customers = customerService.findAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    @PostMapping("/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        customerService.updateCustomer(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer (@PathVariable Integer id){
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable Integer id){
        Customer customer  = customerService.findByCustomerId(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


}
