package com.librarymanagementsystem.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.librarymanagementsystem.example.dao.BookDao;
import com.librarymanagementsystem.example.dto.BookDto;
import com.librarymanagementsystem.example.entity.Book;
import com.librarymanagementsystem.example.exception.BookEntityNotFoundException;


@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookDao dao;

	@Override
	public List<BookDto> getAllBookDetails() {
		List<Book> books = dao.findAll();
		List<BookDto> bookDtos = new ArrayList<>();
		for (Book book : books) {
			bookDtos.add(convertEntityToDto(book));
		}

		return bookDtos;
	}
	
	private BookDto convertEntityToDto(Book entity) {
		BookDto dto = new BookDto();
		BeanUtils.copyProperties(entity, dto);
		return dto;
	}
	
	private Book convertDtoToEntity(BookDto dto) {
		Book entity = new Book();
		BeanUtils.copyProperties(dto, entity);
		return entity;
	}

	@Override
	public BookDto findById(Long id) {
		Optional<Book> optional = dao.findById(id);
		if (!optional.isPresent()) {
			throw new BookEntityNotFoundException("Book entity not present with id:"+id);
		}
		BookDto dto = convertEntityToDto(optional.get());
		return dto;
	}

	@Override
	public BookDto saveBookDetails(BookDto bookDto) {
		Book book = convertDtoToEntity(bookDto);
		book = dao.save(book);
		return convertEntityToDto(book);
	}

	@Override
	public BookDto updateBookDetails(BookDto bookDto) {
		Book book = convertDtoToEntity(bookDto);
		dao.save(book);
		return bookDto;
	}

	@Override
	public void deleteBookById(Long id) {
		Optional<Book> optional = dao.findById(id);
		if (!optional.isPresent()) {
			throw new BookEntityNotFoundException("Book entity not present with id:"+id);
		}
		dao.deleteById(id);
	}
	
	
	

}
