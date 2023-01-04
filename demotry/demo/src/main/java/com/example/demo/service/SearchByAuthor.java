package com.example.demo.service;

import com.example.demo.entity.Book;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByAuthor implements Search {
    @Override
    public List<Book> searchCatalog(List<Book> books) {

//        List<String> slist = Arrays.asList("Tanu", "Kamal", "Suman", "Lucky", "Bunty", "Amit");
//        List<String> sortedList = slist.stream().sorted().collect(Collectors.toList());
//        sortedList.forEach(System.out::println);
        List<Book> authorSort = books.stream()
                .sorted(Comparator.comparing(Book::getBookAuthor)).collect(Collectors.toList());

        System.out.println(authorSort.toString());
//        class Sortbyname implements Comparator<Book> {
//
//            // Method
//            // Sorting in ascending order of name
//            public int compare(Book a, Book b)
//            {
//
//                return a.getBookAuthor().compareTo(b.getBookAuthor());
//            }
//        }

//        List<Book> bookAuthors = new ArrayList<>();


//        for(Book it:books){
//            bookAuthors.add(it);
//        }
//                bookAuthors.add(new Book(LocalDateTime.now().minusDays(4), "Znna Karenina","Leo Tolstoy",500 ));
//                bookAuthors.add(new Book(LocalDateTime.now().minusDays(4), "Anna Karenina","Aeo Tolstoy",50 ));

//        Collections.sort(bookAuthors, new Sortbyname());


//        bookAuthors.sort( Comparator.comparing( String::toString ) );
//        for (Book customer : bookAuthors) {
        //  System.out.println(customer);
        // }
//        List<Book> temp = new ArrayList<Book>();
//        for(int i=0; i<bookAuthors.size(); i++){
//            String currAuth = bookAuthors.get(i);
//            for(int j=0; j<books.size(); j++){
//                if(books.get(j).getBookAuthor() == currAuth){
//                    temp.set(i, books.get(j));
//                    books.remove(j);
//                }
//            }
//        }

        //return bookAuthors;

        //return authorSort;
        // }
        return authorSort;
    }}