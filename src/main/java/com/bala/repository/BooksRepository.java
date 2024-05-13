package com.bala.repository;
import com.bala.model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends MongoRepository<Books,String> {
    @Query("{'BookName': ?0}")
    Books findByName(String name);
    @Query("{'genre': ?0}")
    List<Books> findByGenre(String genre);
    @Query("{'AuthorName': ?0}")
    List<Books> findByAuthor(String author);
}