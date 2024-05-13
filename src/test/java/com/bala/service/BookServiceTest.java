package com.bala.service;

import com.bala.model.Books;
import com.bala.repository.BooksRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    List<Books> Actualbooks =new ArrayList<>();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        Actualbooks.add(new Books("6634b87efd512d69d15955fc", "Harry Potter", "J.K. Rowling", "Pro Publications", "23/03/1999", "Fantasy"));
        Actualbooks.add(new Books("6634c138fd512d69d15955fe", "To Kill a Mockingbird", "Harper Lee", "Bala Publications", "23/04/1992", "Comedy"));
        Actualbooks.add(new Books("6634c1f5fd512d69d15955ff", "The Fault in Our Stars", "John Green", "Balas Publications", "23/04/1992", "Educational"));
        Actualbooks.add(new Books("6634c238fd512d69d1595600", "Ramayanam", "Valmiki", "Valmiki", "20/05/1956", "God"));
        Actualbooks.add(new Books("6634c2b0fd512d69d1595601", "Quran", "Zayd ibn Thabit", "Prophet Muhammad Publications", "02/01/1880", "God"));
        Actualbooks.add(new Books("6634c2fffd512d69d1595602", "Bible", "William Schniedewind", "William Schniedewind", "02/01/1821", "God"));
        Actualbooks.add(new Books("6634c348fd512d69d1595603", "Better English", "Norman Lewis", "Balavishnu Publications", "23/04/1956", "Educational"));
        Actualbooks.add(new Books("6634c385fd512d69d1595604", "Harry Potter Silverstone", "J.K. Rowling", "Pro Publications", "23/03/1999", "Thriller"));
        for(Books b: Actualbooks) booksRepository.save(b);
    }

    @Mock
    private BooksRepository booksRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    void getAllBooks() {
        when(booksRepository.findAll()).thenReturn(Actualbooks);
        List<Books> expectedBooks = bookService.getAllBooks();
        assertEquals(expectedBooks,Actualbooks);
    }

    @Test
    void getById() {
        String id="6634b87efd512d69d15955fc";
        Books f=new Books("6634b87efd512d69d15955fc", "Harry Potter", "J.K. Rowling", "Pro Publications", "23/03/1999", "Fantasy");
        when(booksRepository.findById(id)).thenReturn(Optional.of(f));
        assertEquals(bookService.getById(id),Optional.of(f));
    }

    @Test
    void deleteBooks() {
        String id="6634b87efd512d69d15955fc";
        Books f=new Books("6634b87efd512d69d15955fc", "Harry Potter", "J.K. Rowling", "Pro Publications", "23/03/1999", "Fantasy");
        when(booksRepository.findById(id)).thenReturn(Optional.of(f));
        bookService.DeleteBooks(id);
        verify(booksRepository);
    }

//    @Test
//    void findByName() {
//    }
//
//    @Test
//    void addBooks() {
//    }
//
//    @Test
//    void updateBooks() {
//    }
//
//    @Test
//    void findByGenre() {
//    }
//
//    @Test
//    void findByAuthor() {
//    }
}