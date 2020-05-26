package com.librarymanagementsystem.example.service;

import java.util.List;

import com.librarymanagementsystem.example.dto.BookDto;

public interface BookService {
	
	List<BookDto> getAllBookDetails();
	
	BookDto findById(Long id);
	
	BookDto saveBookDetails(BookDto book);
	
	BookDto updateBookDetails(BookDto book);
	
	void deleteBookById(Long id);
	
	

}
