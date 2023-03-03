package com.example.book.repositrory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book.model.LibraryBook;

@Repository
public interface BookRepo extends JpaRepository<LibraryBook, Integer> {

	List<LibraryBook> findByIsAvailable(boolean isAvailable);

	List<LibraryBook> findByGenre(String genre);

	String findById(String borrowerName);

}
