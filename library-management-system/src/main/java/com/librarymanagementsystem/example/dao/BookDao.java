package com.librarymanagementsystem.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.librarymanagementsystem.example.entity.Book;

public interface BookDao extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book>{

}
