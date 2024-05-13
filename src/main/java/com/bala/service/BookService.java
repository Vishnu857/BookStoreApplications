package com.bala.service;

import com.bala.model.Books;
import com.bala.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BooksRepository booksRepository;
    public List<Books> getAllBooks(){
        return booksRepository.findAll();
    }
    public Optional<Books> getById(String id){
        return booksRepository.findById(id);
    }
    public void DeleteBooks(String id){
        Optional<Books> optionalBook = booksRepository.findById(id);
        if(optionalBook.isPresent()){
            booksRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Book not found with ID: " + id);
        }
    }

    public Books findByName(String name){
        return booksRepository.findByName(name);
    }
    public Books AddBooks(Books b){
        return booksRepository.save(b);
    }

    public Books UpdateBooks( Books u){
        Optional<Books> optionalBook = booksRepository.findById(u.getId());
        if(optionalBook.isPresent()){
            return booksRepository.save(u);
        } else {
            throw new NoSuchElementException("Book not found with ID: " + u.getId());
        }
    }

    public List<Books> findByGenre(String genre){
        return booksRepository.findByGenre(genre);
    }
    public List<Books> findByAuthor(String Author){
        return booksRepository.findByAuthor(Author);
    }


}
