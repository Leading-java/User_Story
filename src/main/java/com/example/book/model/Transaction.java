package com.example.book.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "library_book_id")
	private LibraryBook libraryBook;

	@Column(nullable = false)
	private String borrowerName;

	@Column(nullable = false)
	private LocalDate rentalDate;

	@Column
	private LocalDate returnDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LibraryBook getLibraryBook() {
		return libraryBook;
	}

	public void setLibraryBook(LibraryBook libraryBook) {
		this.libraryBook = libraryBook;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public LocalDate getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(LocalDate rentalDate) {
		this.rentalDate = rentalDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", libraryBook=" + libraryBook + ", borrowerName=" + borrowerName
				+ ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + "]";
	}

	public Transaction(int id, LibraryBook libraryBook, String borrowerName, LocalDate rentalDate,
			LocalDate returnDate) {
		super();
		this.id = id;
		this.libraryBook = libraryBook;
		this.borrowerName = borrowerName;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}

	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

}
