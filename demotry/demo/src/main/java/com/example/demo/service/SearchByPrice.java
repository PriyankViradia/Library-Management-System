package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByPrice implements Search {

    @Override
    public List<Book> searchCatalog(List<Book> books) {


        List<Book> priceSort = books.stream()
                .sorted(Comparator.comparing(Book::getBookPrice)).collect(Collectors.toList());
        return priceSort;

    }
}