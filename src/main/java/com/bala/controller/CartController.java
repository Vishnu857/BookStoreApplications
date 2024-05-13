package com.bala.controller;


import com.bala.model.Books;

import com.bala.service.BookService;
import com.bala.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {
@Autowired
private CartService Cartservice;
    @Autowired
    private BookService service;

    @Operation(summary = "get the cart of the customer", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<Books>> getBookSCart(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(Cartservice.getAllFromCartOfCurrentUser());
    }

    @Operation(summary = "Add a book to the cart of the customer", security = @SecurityRequirement(name = "bearerAuth"))
    @PutMapping("/add/{id}")
    public ResponseEntity<List<Books>> AddToCart(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.CREATED).body(Cartservice.AddToCart(id));
    }
    @Operation(summary = "Remove a book to the cart of the customer", security = @SecurityRequirement(name = "bearerAuth"))

    @PutMapping("/delete/{id}")
    public ResponseEntity<List<Books>> removeFromCart(@PathVariable("id")String id){
        return ResponseEntity.status(HttpStatus.OK).body(Cartservice.RemoveFromCart(id));
    }

}
