package com.library.example.librarymanagementsystem;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import com.librarymanagementsystem.example.LibraryManagementSystemApplication;
import com.librarymanagementsystem.example.dto.BookDto;
import com.librarymanagementsystem.example.entity.Book;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibraryManagementSystemApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LibraryManagementSystemApplicationTests {

		
	 @Autowired
     private TestRestTemplate restTemplate;

     @LocalServerPort
     private int port;

     private String getRootUrl() {
         return "http://localhost:" + port;
     }

     @Test
     public void contextLoads() {

     }

     @Test
     public void testGetAllBooks() {
     HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/book",
        HttpMethod.GET, entity, String.class);  
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetBookById() {
    	BookDto book = restTemplate.getForObject(getRootUrl() + "/book/1", BookDto.class);
        assertNotNull(book);
    }

   @Test
    public void testCreateBook() {
        Book dto = new Book();
        dto.setId(1L);
        dto.setAuthor("John");
        dto.setAvailableBooks(20);
        dto.setBookRefNum("blr-123");
        dto.setName("Data Structures");
        dto.setPrice(100.00);
        ResponseEntity<BookDto> postResponse = restTemplate.postForEntity(getRootUrl() + "/book", dto, BookDto.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateBookDetails() {
        int id = 1;
        BookDto dto = restTemplate.getForObject(getRootUrl() + "/book/"+id, BookDto.class);
        dto.setAuthor("John123");
        dto.setPrice(200.00);
        restTemplate.put(getRootUrl() + "/book", dto);
        BookDto dto2 = restTemplate.getForObject(getRootUrl() + "/book/" + id, BookDto.class);
        assertNotNull(dto2);
    }

    @Test
    public void testDeleteBookById() {
         int id = 1;
         BookDto dto = restTemplate.getForObject(getRootUrl() + "/book/" + id, BookDto.class);
         assertNotNull(dto);
         restTemplate.delete(getRootUrl() + "/book/" + id);
         try {
              dto = restTemplate.getForObject(getRootUrl() + "/book/" + id, BookDto.class);
         } catch (final HttpClientErrorException e) {
              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
         }
    }
  
}
