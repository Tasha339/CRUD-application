package com.crud.application.Service;

import com.crud.application.Controller.CustomerDTO;
import com.crud.application.Entity.Customer;
import com.crud.application.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    
    public void createCustomer(CustomerDTO customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.name);
        customer.setEmail(customerDto.email);
        customer.setAge(customerDto.age);
        customerRepository.save(customer);
    }

    public void removeCustomer(Integer id){
        if(customerRepository.existsById(id))
            customerRepository.deleteById(id);
    }

    public String updateCustomer(
            CustomerDTO customerDto,
            Integer id
    ){
        if(customerRepository.existsById(id)){
            Customer customer = customerRepository.findById(id).orElseThrow();
            if(customerDto.name != null && !customerDto.name.isEmpty())
                customer.setName(customerDto.name);
            if(customerDto.email != null && !customerDto.email.isEmpty())
                customer.setEmail(customerDto.email);
            if(customerDto.age != null)
                customer.setAge(customerDto.age);
            customerRepository.save(customer);
            return "customer updated successfully";
        }else
            return "customer not found";
    }
}

