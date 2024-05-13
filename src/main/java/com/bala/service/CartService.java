package com.bala.service;

import com.bala.config.JwtService;
import com.bala.model.Books;
import com.bala.model.User;
import com.bala.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class CartService {
    @Autowired
    private  BookService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserRepository userRepository;
    public List<Books> getAllFromCartOfCurrentUser(){
        return userRepository.findByUsername(currentUser()).get().getCart();
    }
    public List<Books> AddToCart(String id){
        Optional<Books> book=service.getById(id);
        if(book.isPresent()){
            User u =userRepository.findByUsername(currentUser()).get();
            List<Books> item=u.getCart();
            item.add(book.get());
            u.setCart(item);
            userRepository.save(u);
        }
        return userRepository.findByUsername(currentUser()).get().getCart();
    }
    public List<Books> RemoveFromCart(String id){
        User u =userRepository.findByUsername(currentUser()).get();
        List<Books> l=u.getCart();
        for(Books b:l){
            if(Objects.equals(b.getId(), id)){
                l.remove(b);
                u.setCart(l);
                userRepository.save(u);
            }
        }
        return userRepository.findByUsername(currentUser()).get().getCart();
    }
    public static String currentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();

    }
}
