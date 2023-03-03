package com.example.book.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "library_book")
public class LibraryBook {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int id;

	@Column(name = "title")
	private String title;

	@Column(name = "author")
	private String author;

	@Column(name = "genre")
	private String genre;

	@Column(name = "price")
	private double Price;

	@Column(nullable = false)
	private boolean isAvailable;

	@OneToMany(mappedBy = "libraryBook", cascade = CascadeType.ALL)
	private List<Transaction> transactions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public List<Transaction> getTransaction() {
		return transactions;
	}

	public void setTransaction(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public LibraryBook(int id, String title, String author, String genre, double price, boolean isAvailable,
			List<Transaction> transactions) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.genre = genre;
		Price = price;
		this.isAvailable = isAvailable;
		this.transactions = transactions;
	}

	public LibraryBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LibraryBook [id=" + id + ", title=" + title + ", author=" + author + ", genre=" + genre + ", Price="
				+ Price + ", isAvailable=" + isAvailable + ", transaction=" + transactions + "]";
	}

}