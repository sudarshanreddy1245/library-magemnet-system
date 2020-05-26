package com.librarymanagementsystem.example.dto;

public class BookDto {
	
	private Long id;
	
	private String name;
	
	private String author;
	
	private Double price;
	
	private String bookRefNum;
	
	private Integer availableBooks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getBookRefNum() {
		return bookRefNum;
	}

	public void setBookRefNum(String bookRefNum) {
		this.bookRefNum = bookRefNum;
	}

	public Integer getAvailableBooks() {
		return availableBooks;
	}

	public void setAvailableBooks(Integer availableBooks) {
		this.availableBooks = availableBooks;
	}

}
