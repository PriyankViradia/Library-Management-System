package com.example.demo.service;


import com.example.demo.entity.Book;

import java.util.List;
// The another search we did using strategy pattern where user can search via tittle and author name
public interface Search {
    List<Book> searchCatalog(List<Book> books);
}