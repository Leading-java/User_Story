package com.example.book.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.exception.BookNotAvailableException;
import com.example.book.exception.BookNotFoundException;
import com.example.book.model.LibraryBook;
import com.example.book.model.Transaction;
import com.example.book.repositrory.TransactionRepository;
import com.example.book.service.BookService;

@RestController
@RequestMapping(value = "/Books")
public class BookController {

	@Autowired
	private BookService bookservice;

	@Autowired
	private TransactionRepository transactionRepository;

	// Add new book to library
	@PostMapping("/addbook")
	public ResponseEntity<LibraryBook> addBook(@RequestBody LibraryBook libraryBook) {
		LibraryBook newBook = bookservice.save(libraryBook);
		if (newBook != null) {
			return new ResponseEntity<>(newBook, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// Update book details
	@PutMapping("/{id}")
	public ResponseEntity<LibraryBook> updateBook(@PathVariable int id, @RequestBody LibraryBook libraryBook) {
		LibraryBook updatedBook = bookservice.updateBook(id, libraryBook);
		return new ResponseEntity<>(updatedBook, HttpStatus.OK);
	}

	// Get all genres
	@GetMapping("/genres")
	public ResponseEntity<List<String>> getAllGenres() {
		List<LibraryBook> books = bookservice.findAllGenres();
		List<String> genres = books.stream().map(LibraryBook::getGenre).distinct().collect(Collectors.toList());
		return new ResponseEntity<>(genres, HttpStatus.OK);
	}

	// Get books by genre
	@GetMapping(params = "genre")
	public ResponseEntity<List<LibraryBook>> getBooksByGenre(@RequestParam String genre) {
		List<LibraryBook> libraryBooks = bookservice.findbyGenres(genre);
		return new ResponseEntity<>(libraryBooks, HttpStatus.OK);
	}

	@PostMapping("/{id}/rent")
	public ResponseEntity<String> rentBook(@PathVariable int id, @RequestParam("borrowerName") String borrowerName) {
		try {
			bookservice.rentBook(id, borrowerName);
			return new ResponseEntity<>("book opt successful", HttpStatus.OK);
		} catch (BookNotFoundException e) {
			return new ResponseEntity<String>("book not found", HttpStatus.NOT_FOUND);
		} catch (BookNotAvailableException e) {
			return new ResponseEntity<String>(" Book not Available ", HttpStatus.CONFLICT);
		}

	}

}
