package com.example.book.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book.exception.BookNotAvailableException;
import com.example.book.exception.BookNotFoundException;
import com.example.book.model.LibraryBook;
import com.example.book.model.Transaction;
import com.example.book.repositrory.BookRepo;
import com.example.book.repositrory.TransactionRepository;

@Service
public class BookService {

	@Autowired
	private BookRepo bookRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	public LibraryBook save(LibraryBook libraryBook) {

		return bookRepository.save(libraryBook);
	}

	// Update book details
	public LibraryBook updateBook(int id, LibraryBook libraryBook) {
		LibraryBook existingBook = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

		existingBook.setTitle(libraryBook.getTitle());
		existingBook.setAuthor(libraryBook.getAuthor());
		existingBook.setGenre(libraryBook.getGenre());
		existingBook.setPrice(libraryBook.getPrice());

		return bookRepository.save(existingBook);
	}

	public List<LibraryBook> findbyGenres(String genre) {

		return bookRepository.findByGenre(genre);
	}

	public Optional<LibraryBook> findById(int bookId) {

		return bookRepository.findById(bookId);
	}

	public List<LibraryBook> findAllGenres() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	public void rentBook(int id, String borrowerName) throws BookNotFoundException, BookNotAvailableException {
		LibraryBook book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));

		if (!book.isAvailable()) {
			throw new BookNotAvailableException("Book not available");
		}

		Transaction transaction = new Transaction();
		transaction.setLibraryBook(book);
		transaction.setBorrowerName(borrowerName); // userService is not shown in this example
		transaction.setRentalDate(LocalDate.now());

		book.setAvailable(false);
		book.getTransaction().add(transaction);

		bookRepository.save(book);
		transactionRepository.save(transaction);
	}

}
