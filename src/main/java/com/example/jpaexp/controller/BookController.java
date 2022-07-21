package com.example.jpaexp.controller;

import com.example.jpaexp.model.Book;
import com.example.jpaexp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    BookService bookService;
    @GetMapping("/findAll")
    public void findAll() {
        PageRequest pageable = PageRequest.of(2, 3);
        Page<Book> page = bookService.getBookByPage(pageable);
        System.out.println("total pages:"+page.getTotalPages());
        System.out.println("total records:"+page.getTotalElements());
        System.out.println("query result:"+page.getContent());
        System.out.println("current page:"+(page.getNumber()+1));
        System.out.println("current records:"+page.getNumberOfElements());
        System.out.println("records per page:"+page.getSize());
    }
    @GetMapping("/search")
    public void search() {
        List<Book> bs1 = bookService.getBookByIdAndAuthor("罗贯中", 1);
        List<Book> bs2 = bookService.getBooksByAuthorStartingWith("吴");
        List<Book> bs3 = bookService.getBooksByIdAndName("西", 8);
        List<Book> bs4 = bookService.getBooksByPriceGreaterThan(30F);
        Book b = bookService.getMaxIdBook();
        System.out.println("bs1:"+bs1);
        System.out.println("bs2:"+bs2);
        System.out.println("bs3:"+bs3);
        System.out.println("bs4:"+bs4);
        System.out.println("b:"+b);
    }
    @GetMapping("/save")
    public void save() {
        Book book = new Book();
        book.setAuthor("鲁迅");
        book.setName("呐喊");
        book.setPrice(23F);
        bookService.addBook(book);
    }
}
