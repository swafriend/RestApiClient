package com.example.biz.service;

import com.example.biz.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class RestTemplateService {

    @Autowired
    private RestOperations restOperations;

    public void func1() {

        Customer customer = new Customer();
        customer.setId(4);
        customer.setName("wadasadamu");
        customer.setEmailAddress("wada@itrade.co.jp");
        customer.setBirthday(LocalDate.now());
        customer.setFavoriteNumber(4);

        //Customer resouce = restOperations.getForObject("http://localhost:8080/Gradle___Sample___web20200616_2_1_0_SNAPSHOT_war/api/customer/1", Customer.class);
        URI createdResouceUri = restOperations.postForLocation("http://localhost:8080/Gradle___Sample___web20200616_2_1_0_SNAPSHOT_war/api/customer", customer);
        System.out.println(createdResouceUri);
    }


    public void func2() {
        Customer customer = new Customer();
        customer.setId(4);
        customer.setName("wadasadamu");
        customer.setEmailAddress("wada@itrade.co.jp");
        customer.setBirthday(LocalDate.now());
        customer.setFavoriteNumber(4);

        RequestEntity<Customer> requestEntity = RequestEntity.post(URI.create("http://localhost:8080/Gradle___Sample___web20200616_2_1_0_SNAPSHOT_war/api/customer"))
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Track-Id", UUID.randomUUID().toString())
                .body(customer);

        ResponseEntity<Void> responseEntity = restOperations.exchange(requestEntity, Void.class);

        HttpStatus httpStatus = responseEntity.getStatusCode();
        System.out.println(httpStatus);
        HttpHeaders responseHeaders = responseEntity.getHeaders();
        System.out.println(responseHeaders);

    }

    public void func3() {

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/Gradle___Sample___web20200616_2_1_0_SNAPSHOT_war/api/customer/{customerId}")
                .buildAndExpand("3")
                .encode()
                .toUri();

        RequestEntity<Void> requestEntity = RequestEntity.get(uri).header("X-Track-Id", UUID.randomUUID().toString()).build();

        ResponseEntity<Customer> resouce = restOperations.exchange(requestEntity, Customer.class);

        System.out.println(resouce);
    }

}
