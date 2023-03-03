package com.example.book.exception;

public class BookNotAvailableException extends RuntimeException {
	
		private static final long serialVersionUID = 1L;

		public BookNotAvailableException(String message) {
		        super(message);
		    }
}
