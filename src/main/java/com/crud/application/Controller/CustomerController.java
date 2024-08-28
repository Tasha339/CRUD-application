package com.crud.application.Controller;

import com.crud.application.Entity.Customer;
import com.crud.application.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService =  new CustomerService();


    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }


    @PostMapping
    public void addCustomer(@RequestBody CustomerDTO customerDto){
        customerService.createCustomer(customerDto);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable("id") Integer id){
        customerService.removeCustomer(id);
    }

    @PutMapping("{id}")
    public String updateCustomer(
            @RequestBody CustomerDTO customerDto,
            @PathVariable("id") Integer id
    ){
        return customerService.updateCustomer(customerDto, id);
    }
}

