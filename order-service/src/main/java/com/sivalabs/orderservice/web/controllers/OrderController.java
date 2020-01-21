package com.sivalabs.orderservice.web.controllers;

import com.sivalabs.orderservice.entities.Order;
import com.sivalabs.orderservice.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@RestController
public class OrderController {

    private OrderRepository repo;

    @Autowired
    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    @RequestMapping(path="/api/orders",method=RequestMethod.POST)
    public ResponseEntity<Order> createOrder(@RequestBody Order order,HttpServletRequest request) {
    	 String auth_header = request.getHeader("AUTH_HEADER");
        return new ResponseEntity<Order>(repo.save(order),HttpStatus.OK);
    }

    @GetMapping("/api/orders/{id}")
    public Optional<Order> findOrderById(@PathVariable Long id) {
        return repo.findById(id);
    }

}
