package com.librarymanagementsystem.example.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.librarymanagementsystem.example.dto.BookDto;
import com.librarymanagementsystem.example.service.BookService;

@RestController
public class BookController {
	
	@Autowired
	private BookService service;
	
	@GetMapping("/book")
	public ResponseEntity<List<BookDto>> getAllBookDetails() {
		List<BookDto> books = service.getAllBookDetails();
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/book/{id}")
	public Resource<BookDto> findById(@PathVariable Long id) {
		BookDto bookDto = service.findById(id);
		Resource<BookDto> resource = new Resource<BookDto>(bookDto);
		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).getAllBookDetails());
		resource.add(builder.withRel("book-details"));
		return resource;
	}
	
	@PostMapping("/book")
	public ResponseEntity<BookDto> saveBookDetails(@Valid @RequestBody BookDto dto) {
		dto = service.saveBookDetails(dto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/book")
	public Resource<BookDto> updateBookDetails(@Valid @RequestBody BookDto dto) {
		dto = service.updateBookDetails(dto);
		Resource<BookDto> resource = new Resource<BookDto>(dto);
		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).getAllBookDetails());
		resource.add(builder.withRel("book-details"));
		return resource;	
	}

	@DeleteMapping("/book/{id}")
	public Resource<String> deleteBookById(@PathVariable Long id) {
		service.deleteBookById(id);
		Resource<String> resource = new Resource<String>("Successfully Deleted");
		ControllerLinkBuilder builder = linkTo(methodOn(this.getClass()).getAllBookDetails());
		resource.add(builder.withRel("book-details"));
		return resource;
	}

	
 
	

}
