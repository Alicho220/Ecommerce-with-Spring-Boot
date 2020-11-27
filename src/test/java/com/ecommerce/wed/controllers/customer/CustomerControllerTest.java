package com.ecommerce.wed.controllers.customer;

import com.ecommerce.data.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.http.RequestEntity.post;

@SpringBootTest
@AutoConfigureMockMvc
//@AutoConfigureRestDocs
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper;
    Customer customer;


    @BeforeEach
    void setUp() {
        customer = new Customer();
        mapper = new ObjectMapper();
    }

    @Test
    void testCreateCustomerEndpoint_thenReturnOk() throws Exception{
        customer.setFirstName("Emeka");
        customer.setLastName("Alicho");
        customer.setPassword("alicho123");
        customer.setContact("08035645766");
        customer.setEmail("jimmyo@gmail.com");

        this.mockMvc.perform(
                post("/customer/create")
                .contentType("application/json")
                .content(mapper.writeValueAsString(customer))
                ).andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

    }
    @Test
    void testFindAllCustomerEndPoint_thenReturnCustomers () throws Exception {
        this.mockMvc.perform(
                get("/customer/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

    }
}