package com.myproject.reacitve.mappers;

import com.myproject.reacitve.domain.Customer;
import com.myproject.reacitve.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDto(Customer customer);
}
