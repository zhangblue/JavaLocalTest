package com.zhangblue.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Collectors toMap demo
 *
 * @author zhangdi
 */
public class CollectorsToMapDemo {

  public static void main(String[] args) {
    new CollectorsToMapDemo().demo2();
  }


  public void demo() {
    List<Book> bookList = new ArrayList<>();
    bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
    bookList.add(new Book("The Two Towers", 1954, "0345339711"));
    bookList.add(new Book("The Return of the King", 1955, "0618129111"));

    Map<Integer, Book> collect = bookList.stream()
        .collect(Collectors
            .toMap(Book::getReleaseYear, Function.identity(), (oldKey, newKey) -> oldKey));

    collect.forEach((k, v) -> System.out.println(k + "_" + v.toString()));
  }


  public void demo2() {
    List<Book> bookList = new ArrayList<>();
    bookList.add(new Book("The Fellowship of the Ring", 1954, "0395489318"));
    bookList.add(new Book("The Two Towers", 1954, "0345339711"));
    bookList.add(new Book("The Return of the King", 1955, "0618129111"));

    Map<String, String> collect = bookList.stream()
        .collect(Collectors
            .toMap(book -> book.getReleaseYear() + "_" + book.getIsbn(), book -> book.getName(),
                (oldKey, newKey) -> oldKey));

    collect.forEach((k, v) -> System.out.println(k + "=========" + v.toString()));

  }

  class Book {

    private String name;
    private int releaseYear;
    private String isbn;

    public Book(String name, int releaseYear, String isbn) {
      this.name = name;
      this.releaseYear = releaseYear;
      this.isbn = isbn;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getReleaseYear() {
      return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
      this.releaseYear = releaseYear;
    }

    public String getIsbn() {
      return isbn;
    }

    public void setIsbn(String isbn) {
      this.isbn = isbn;
    }

    @Override
    public String toString() {
      return "Book{" +
          "name='" + name + '\'' +
          ", releaseYear=" + releaseYear +
          ", isbn='" + isbn + '\'' +
          '}';
    }
  }
}
